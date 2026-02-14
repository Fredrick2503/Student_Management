package service;

import config.DatabaseConfig;
import config.TransactionManager;
import exception.DuplicateStudentException;
import exception.StudentNotFoundException;
import model.Student;
import repository.StudentRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class StudentService implements StudentServiceInterface {
//    private final Map<String, Student> students;
    private final StudentRepository repository;
    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);
    private static final TransactionManager transactionManger = new TransactionManager();

    public StudentService(StudentRepository repository) {
        this.repository = repository;

//        List<Student> loaded = this.repository.loadStudents();
//        this.students = new ConcurrentHashMap<>(Math.max(16, loaded.size() * 2));
//        logger.info("StudentService initialized. Loaded {} students", loaded.size());
//        for (Student s : loaded) {
//            if (students.containsKey(s.getUSN())) {
//                logger.warn("Duplicate insertion attempted: {}", s.getUSN());
//                throw new DuplicateStudentException(s.getUSN());
//            }
//            students.put(s.getUSN(), s);
//        }
    }

//    private void save() {
//        this.repository.saveStudents(new ArrayList<>(students.values()));
//    }

    private String normalizeUsn(String usn) {
        return usn.trim().toUpperCase();
    }


    //READ
    @Override
    public Optional<Student> getStudent(String usn) {
        try(Connection conn = DatabaseConfig.getConnection()){
            return  repository.findByUsn(conn,usn);
        }catch (SQLException e){
            throw new RuntimeException("Database error: ",e);
        }
    }

    public List<Student> getAllStudents() {
        try(Connection conn = DatabaseConfig.getConnection()){
            return  repository.findAll(conn);
        }catch (SQLException e){
            throw new RuntimeException("Database error: ",e);
        }
    }

    public List<Student> findStudentsByName(String name) {
        try(Connection conn = DatabaseConfig.getConnection()){
            return  repository.findByName(conn,name);
        }catch (SQLException e){
            throw new RuntimeException("Database error: ",e);
        }
    }

//    public List<Student> getStudentsSortedByCgpa() {
//        List<Student> sorted = new ArrayList<>(students.values());
//        sorted.sort(Comparator.comparingDouble(Student::getCgpa).reversed());
//        return sorted;
//    }

    public List<Student> getStudentsBySemester(int sem) {
        try(Connection conn = DatabaseConfig.getConnection()){
            return  repository.findBySemester(conn,sem);
        }catch (SQLException e){
            throw new RuntimeException("Database error: ",e);
        }
    }

//    public List<Student> getStudentsCgpaSortedBySemster(int sem) {
//        List<Student> result = getStudentBySemester(sem);
//        result.sort(Comparator.comparingDouble(Student::getCgpa).reversed());
//        return result;
//    }

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
        transactionManger.execute(conn->{
            repository.save(conn,student);
        });
    }

    public void updateStudent(String usn, Consumer<Student> updater) {
        Optional<Student> student = getStudent(usn);
        if (student.isPresent()) {
            updater.accept(student.get());
            transactionManger.execute(conn->{
                repository.save(conn,student.get());
            });
            logger.info("Student updates: {}", student.get().getUSN());
        }
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


    public void removeStudent(String usn) {
        transactionManger.execute(conn->{
            repository.deleteByUsn(conn,usn);
        });
        logger.info("Student removed: {}",usn);
//        return removed;
    }

}

