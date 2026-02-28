//package com.students.service;
//
//import com.students.config.DatabaseConfig;
//import com.students.config.TransactionManager;
//import com.students.exception.DuplicateStudentException;
//import com.students.exception.StudentNotFoundException;
//import com.students.model.Student;
//import org.springframework.stereotype.Service;
//import com.students.repository.StudentRepository;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.*;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.function.Consumer;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//@Service
//public class StudentService implements StudentServiceInterface {
////    private final Map<String, Student> students;
//    private final StudentRepository com.students.repository;
//    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);
//    private static final TransactionManager transactionManger = new TransactionManager();
//
//    public StudentService(StudentRepository com.students.repository) {
//        this.com.students.repository = com.students.repository;
//
////        List<Student> loaded = this.com.students.repository.loadStudents();
////        this.students = new ConcurrentHashMap<>(Math.max(16, loaded.size() * 2));
////        logger.info("StudentService initialized. Loaded {} students", loaded.size());
////        for (Student s : loaded) {
////            if (students.containsKey(s.getUSN())) {
////                logger.warn("Duplicate insertion attempted: {}", s.getUSN());
////                throw new DuplicateStudentException(s.getUSN());
////            }
////            students.put(s.getUSN(), s);
////        }
//    }
//
////    private void save() {
////        this.com.students.repository.saveStudents(new ArrayList<>(students.values()));
////    }
//    //READ
//    @Override
//    public Optional<Student> getStudent(String usn) {
//        try(Connection conn = DatabaseConfig.getConnection()){
//            return  com.students.repository.findByUsn(conn,usn);
//        }catch (SQLException e){
//            throw new RuntimeException("Database error: ",e);
//        }
//    }
//
//    public List<Student> getAllStudents() {
//        try(Connection conn = DatabaseConfig.getConnection()){
//            return  com.students.repository.findAll(conn);
//        }catch (SQLException e){
//            throw new RuntimeException("Database error: ",e);
//        }
//    }
//
//    public List<Student> findStudentsByName(String name) {
//        try(Connection conn = DatabaseConfig.getConnection()){
//            return  com.students.repository.findByName(conn,name);
//        }catch (SQLException e){
//            throw new RuntimeException("Database error: ",e);
//        }
//    }
//
////    public List<Student> getStudentsSortedByCgpa() {
////        List<Student> sorted = new ArrayList<>(students.values());
////        sorted.sort(Comparator.comparingDouble(Student::getCgpa).reversed());
////        return sorted;
////    }
//
//    public List<Student> getStudentsBySemester(int sem) {
//        try(Connection conn = DatabaseConfig.getConnection()){
//            return  com.students.repository.findBySemester(conn,sem);
//        }catch (SQLException e){
//            throw new RuntimeException("Database error: ",e);
//        }
//    }
//
////    public List<Student> getStudentsCgpaSortedBySemster(int sem) {
////        List<Student> result = getStudentBySemester(sem);
////        result.sort(Comparator.comparingDouble(Student::getCgpa).reversed());
////        return result;
////    }
//
//    //    public void addStudent(Student student) {
////        if (students.containsKey(student.getUSN())) {
////            logger.warn("Duplicate insertion attempted: {}",student.getUSN());
////            throw new DuplicateStudentException(student.getUSN());
////        }
////        students.put(student.getUSN(), student);
////        save();
////        logger.info("Student add: {}", student.getUSN());
////    }
//    public void addStudent(Student student) {
//        transactionManger.execute(conn->{
//            if(com.students.repository.findByUsn(conn,student.getUSN()).isPresent()){
//                throw new DuplicateStudentException(student.getUSN());
//            }
//            com.students.repository.save(conn,student);
//            com.students.repository.insertAudit(
//                    conn,
//                    "STUDENT",
//                    student.getUSN(),
//                    "CREATE",
//                    null,
//                    student.toString()
//            );
//        });
//        logger.info("Student create: {}",student.getUSN());
//    }
//
//    public void updateStudent(String usn, Consumer<Student> updater) {
//       transactionManger.execute(conn->{
//           Optional<Student> existing = com.students.repository.findByUsn(conn,usn);
//           if(existing.isEmpty()){
//               throw new StudentNotFoundException(usn);
//           }
//           Student current = existing.get();
//           Student updated = new Student(
//                   current.getUSN(),
//                   current.getName(),
//                   current.getSem(),
//                   current.getCgpa()
//           );
//           updater.accept(updated);
//           com.students.repository.save(conn,updated);
//           com.students.repository.insertAudit(
//                   conn,
//                   "STUDENT",
//                   usn,
//                   "UPDATE",
//                   current.toString(),
//                   updated.toString()
//           );
//       });
//       logger.info("Student updated: {}", usn);
//    }
//
//    public void updateCgpa(String usn, double cgpa) {
//        updateStudent(usn, s -> s.setCgpa(cgpa));
//    }
//
//    public void updateSem(String usn, int sem) {
//        updateStudent(usn, s -> s.setSem(sem));
//    }
//
//    public void updateName(String usn, String name) {
//        updateStudent(usn, s -> s.setName(name));
//    }
//
//
//    public void removeStudent(String usn) {
//        transactionManger.execute(conn->{
//            int deleted = com.students.repository.deleteByUsn(conn,usn);
//            if(deleted==0){
//                throw new StudentNotFoundException(usn);
//            }
//            com.students.repository.insertAudit(
//                    conn,
//                    "STUDENT",
//                    usn,
//                    "DELETE",
//                    null,
//                    null
//                    );
//        });
//        logger.warn("Student removed: {}",usn);
////        return removed;
//    }
//
//}
//



package com.students.service;

import com.students.exception.DuplicateStudentException;
import com.students.exception.StudentNotFoundException;
import com.students.model.Student;
import com.students.repository.StudentRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service
public class StudentService implements StudentServiceInterface {

    private static final Logger logger =
            LoggerFactory.getLogger(StudentService.class);

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    // ------------------------
    // CREATE
    // ------------------------
    @Transactional
    public void addStudent(Student student) {

        if (repository.findByUsn(student.getUSN()).isPresent()) {
            throw new DuplicateStudentException(student.getUSN());
        }

        repository.save(student);

        repository.insertAudit(
                "STUDENT",
                student.getUSN(),
                "CREATE",
                null,
                student.toString()
        );

        logger.info("Student created: {}", student.getUSN());
    }

    // ------------------------
    // READ
    // ------------------------

    @Transactional(readOnly = true)
    @Override
    public Student getStudent(String usn) {
        return repository.findByUsn(usn)
                .orElseThrow(() -> new StudentNotFoundException(usn));
    }

    @Transactional(readOnly = true)
    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Student> findStudentsByName(String name) {
        return repository.findByName(name);
    }

    @Transactional(readOnly = true)
    public List<Student> getStudentsBySemester(int sem) {
        return repository.findBySemester(sem);
    }

    // ------------------------
    // UPDATE
    // ------------------------
    @Transactional
    public void updateStudent(String usn, Consumer<Student> updater) {

        Student current = repository.findByUsn(usn)
                .orElseThrow(() -> new StudentNotFoundException(usn));

        String oldState = current.toString();

        updater.accept(current);

        repository.save(current);

        repository.insertAudit(
                "STUDENT",
                usn,
                "UPDATE",
                oldState,
                current.toString()
        );

        logger.info("Student updated: {}", usn);
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

    // ------------------------
    // DELETE
    // ------------------------
    @Transactional
    public void  removeStudent(String usn) {

        int deleted = repository.deleteByUsn(usn);

        if (deleted == 0) {
            throw new StudentNotFoundException(usn);
        }

        repository.insertAudit(
                "STUDENT",
                usn,
                "DELETE",
                null,
                null
        );

        logger.info("Student deleted: {}", usn);
    }
}