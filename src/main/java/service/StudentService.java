package service;

import exception.DuplicateStudentException;
import exception.StudentNotFoundException;
import model.Student;
import repository.StudentRepository;
import repository.FileStudentRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class StudentService implements StudentServiceInterface {
    private final Map<String, Student> students;
    private final StudentRepository repository;
    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);


    public StudentService(StudentRepository repository) {
        this.repository = repository;
        List<Student> loaded = this.repository.loadStudents();
        this.students = new ConcurrentHashMap<>(Math.max(16, loaded.size() * 2));
        logger.info("StudentService initialized. Loaded {} students", loaded.size());
        for (Student s : loaded) {
            if (students.containsKey(s.getUSN())) {
                logger.warn("Duplicate insertion attempted: {}", s.getUSN());
                throw new DuplicateStudentException(s.getUSN());
            }
            students.put(s.getUSN(), s);
        }
    }

    private void save() {
        this.repository.saveStudents(new ArrayList<>(students.values()));
    }

    private String normalizeUsn(String usn) {
        return usn.trim().toUpperCase();
    }


    public Student getStudent(String usn) {
        Student student = students.get(normalizeUsn(usn));
        if (student == null) {
            logger.warn("Student lookup failed: {}", usn);
            throw new StudentNotFoundException(usn.toUpperCase());
        }
        return student;
    }

    public Student removeStudent(String usn) {
        Student removed = students.remove(normalizeUsn(usn));
        if (removed == null) {
            logger.error("Student lookup failed: {}", usn);
            throw new StudentNotFoundException(usn.toUpperCase());
        }
        save();
        logger.info("Student removed: {}", removed.getUSN());
        return removed;
    }

    //    public void addStudent(Student student) {
//        if (students.containsKey(student.getUSN())) {
//            logger.warn("Duplicate insertion attempted: {}",student.getUSN());
//            throw new DuplicateStudentException(student.getUSN());
//        }
//        students.put(student.getUSN(), student);
//        save();
//        logger.info("Student add: {}", student.getUSN());
//    }
    public void addStudent(Student student) {
        String usn = student.getUSN();
        Student existing = students.putIfAbsent(usn, student);

        if (existing != null) {
            logger.warn("Duplicate insertion attempted: {}", usn);
            throw new DuplicateStudentException(usn);
        }

        try {
            save();
            logger.info("Student added: {}", usn);
        } catch (Exception e) {

            students.remove(usn); // rollback memory

            logger.error("Failed to persist student: {}", usn, e);

            throw e;
        }
    }

    public void updateStudent(String usn, Consumer<Student> updater) {
        Student student = getStudent(usn);
        updater.accept(student);
        save();
        logger.info("Student updates: {}", student.getUSN());
    }

    public void updateCgpa(String usn, double cgpa) {
        updateStudent(usn, s -> s.setCgpa(cgpa));
    }

    public void updateSem(String usn, int sem) {
        updateStudent(usn, s -> s.setSem(sem));
    }

    public void updateName(String usn, String name) {
        updateStudent(usn, s -> s.setName(name));
    }

    public List<Student> getAllStudents() {
        return Collections.unmodifiableList(new ArrayList<>(students.values()));
    }

    public List<Student> findStudentsByName(String name) {
        if (name == null || name.isBlank()) {
            return Collections.emptyList();
        }
        String search = name.trim().toLowerCase();
        List<Student> result = new ArrayList<>();
        for (Student student : students.values()) {
            String studentName = student.getName().toLowerCase();
            if (studentName.contains(search)) {
                result.add(student);
            }
        }
        return result;
    }

    public List<Student> getStudentsSortedByCgpa() {
        List<Student> sorted = new ArrayList<>(students.values());
        sorted.sort(Comparator.comparingDouble(Student::getCgpa).reversed());
        return sorted;
    }

    public List<Student> getStudentBySemester(int sem) {
        List<Student> result = new ArrayList<>();
        for (Student s : students.values()) {
            if (s.getSem() == sem) {
                result.add(s);
            }
        }
        return result;
    }

    public List<Student> getStudentsCgpaSortedBySemster(int sem) {
        List<Student> result = getStudentBySemester(sem);
        result.sort(Comparator.comparingDouble(Student::getCgpa).reversed());
        return result;
    }


}

