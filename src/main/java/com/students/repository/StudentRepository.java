package com.students.repository;
import com.students.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {
//    List<Student> loadStudents();
//    void saveStudents(Collection<Student> Students);
    void save( Student student);
    Optional<Student> findByUsn(String usn);
    int deleteByUsn(String usn);
    List<Student> findAll();
    List<Student> findByName(String name);
    List<Student> findBySemester( int sem);
    public void insertAudit(
                            String entityName,
                            String entityId,
                            String operation,
                            String oldValue,
                            String newValue);
}
