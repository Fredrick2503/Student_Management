package repository;
import model.Student;

import java.sql.Connection;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface StudentRepository {
//    List<Student> loadStudents();
//    void saveStudents(Collection<Student> Students);
    void save(Connection conn, Student student);
    Optional<Student> findByUsn(Connection conn,String usn);
    void deleteByUsn(Connection conn,String usn);
    List<Student> findAll(Connection conn);
    List<Student> findByName(Connection conn,String name);
    List<Student> findBySemester(Connection conn, int sem);
    void insertAudit(Connection conn, String usn,String action);
}
