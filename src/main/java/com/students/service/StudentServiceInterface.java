package com.students.service;

import com.students.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentServiceInterface {

//    void addStudent(Student student);
//
//    Optional<Student> getStudent(String usn);
//
//    Student removeStudent(String usn);
//
//    List<Student> getAllStudents();
//
//    List<Student> findStudentsByName(String name);
//
//    void updateCgpa(String usn, double cgpa);
//
//    void updateName(String usn, String name);
//
//    void updateSem(String usn, int sem);
//
//    List<Student> getStudentsBySemester(int sem);
    // WRITE
    void addStudent(Student student);

    void removeStudent(String usn);

    void updateCgpa(String usn, double cgpa);

    void updateName(String usn, String name);

    void updateSem(String usn, int sem);

//    void enrollStudentWithAudit(Student student);

    // READ
    Student getStudent(String usn);

    List<Student> getAllStudents();

    List<Student> findStudentsByName(String name);

    List<Student> getStudentsBySemester(int sem);
}
