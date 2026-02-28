//package com.students.repository;
//
//import com.students.config.DatabaseConfig;
//import com.students.model.Student;
//
//import java.sql.*;
//import java.util.*;
//
//public class PostgresStudentRepository implements StudentRepository {
//    //    @Override
////    public List<Student> loadStudents(){
////        return new ArrayList<>();
////    }
////
////    public void saveStudents(Collection<Student> Students){
////        return;
////    }
//    @Override
//    public Optional<Student> findByUsn(Connection conn, String usn) {
//        String sql = "SELECT usn,name,cgpa,sem FROM students WHERE usn = ?";
//        try (PreparedStatement ps = conn.prepareStatement(sql)) {
//            ps.setString(1, usn);
//            try (ResultSet rs = ps.executeQuery()) {
//                if (rs.next()) {
//                    Student student = new Student(
//                            rs.getString("usn"),
//                            rs.getString("name"),
//                            rs.getInt("sem"),
//                            rs.getDouble("cgpa")
//                    );
//                    return Optional.of(student);
//                }
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException("Database error", e);
//        }
//        return Optional.empty();
//    }
//
//    @Override
//    public void save(Connection conn, Student student) {
//        String sql = """
//                INSERT INTO students (usn,name,cgpa,sem)
//                VALUES (?,?,?,?)
//                ON CONFLICT (usn)
//                DO UPDATE SET
//                    name = EXCLUDED.name,
//                    cgpa = EXCLUDED.cgpa,
//                    sem = EXCLUDED.sem,
//                    updated_at = CURRENT_TIMESTAMP
//                """;
//        try (PreparedStatement ps = conn.prepareStatement(sql)) {
//            ps.setString(1, student.getUSN());
//            ps.setString(2, student.getName());
//            ps.setDouble(3, student.getCgpa());
//            ps.setInt(4, student.getSem());
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException("Database error", e);
//        }
//    }
//
//    public int deleteByUsn(Connection conn, String usn) {
//        String sql = """
//                DELETE FROM students WHERE usn = ?
//                """;
//        try (PreparedStatement ps = conn.prepareStatement(sql)) {
//            ps.setString(1, usn);
//            return ps.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException("Database error: ", e);
//        }
//
//    }
//
//    @Override
//    public List<Student> findAll(Connection conn) {
//        String sql = """
//                SELECT usn,name,cgpa,sem FROM students
//                """;
//        List<Student> students = new ArrayList<>();
//        try (PreparedStatement ps = conn.prepareStatement(sql)) {
//            try (ResultSet rs = ps.executeQuery()) {
//                while (rs.next()) {
//                    Student student = new Student(
//                            rs.getString("usn"),
//                            rs.getString("name"),
//                            rs.getInt("sem"),
//                            rs.getDouble("cgpa")
//                    );
//                    students.add(student);
//                }
//                return students;
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException("Database error: ", e);
//        }
//    }
//
//    @Override
//    public List<Student> findByName(Connection conn, String name) {
//        String sql = """
//                SELECT usn,name,cgpa,sem FROM students WHERE LOWER(name) LIKE LOWER(?)
//                """;
//        List<Student> students = new ArrayList<>();
//        try (PreparedStatement ps = conn.prepareStatement(sql)) {
//            ps.setString(1, "%" + name + "%");
//            try (ResultSet rs = ps.executeQuery()) {
//                while (rs.next()) {
//                    Student student = new Student(
//                            rs.getString("usn"),
//                            rs.getString("name"),
//                            rs.getInt("sem"),
//                            rs.getDouble("cgpa")
//                    );
//                    students.add(student);
//                }
//                return students;
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException("Database error: ", e);
//        }
//    }
//
//    @Override
//    public List<Student> findBySemester(Connection conn, int sem) {
//        String sql = """
//                SELECT usn,name,cgpa,sem FROM students WHERE sem = ?
//                """;
//        List<Student> students = new ArrayList<>();
//        try (PreparedStatement ps = conn.prepareStatement(sql)) {
//            ps.setInt(1, sem);
//            try (ResultSet rs = ps.executeQuery()) {
//                while (rs.next()) {
//                    Student student = new Student(
//                            rs.getString("usn"),
//                            rs.getString("name"),
//                            rs.getInt("sem"),
//                            rs.getDouble("cgpa")
//                    );
//                    students.add(student);
//                }
//                return students;
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException("Database error: ", e);
//        }
//    }
//
//    @Override
//    public void insertAudit(Connection conn,
//                            String entityName,
//                            String entityId,
//                            String operation,
//                            String oldValue,
//                            String newValue) {
//
//        String sql = """
//                INSERT INTO audit_logs
//                (entity_name, entity_id, operation, old_value, new_value)
//                VALUES (?, ?, ?, ?, ?)
//                """;
//
//        try (PreparedStatement ps = conn.prepareStatement(sql)) {
//            ps.setString(1, entityName);
//            ps.setString(2, entityId);
//            ps.setString(3, operation);
//            ps.setString(4, oldValue);
//            ps.setString(5, newValue);
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException("Database error", e);
//        }
//    }
//}

package com.students.repository;

import com.students.model.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PostgresStudentRepository implements StudentRepository {

    private final JdbcTemplate jdbcTemplate;

    public PostgresStudentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // -----------------------
    // FIND BY USN
    // -----------------------
    @Override
    public Optional<Student> findByUsn(String usn) {

        String sql = "SELECT usn,name,sem,cgpa FROM students WHERE usn = ?";

        List<Student> result = jdbcTemplate.query(
                sql,
                (rs, rowNum) -> new Student(
                        rs.getString("usn"),
                        rs.getString("name"),
                        rs.getInt("sem"),
                        rs.getDouble("cgpa")
                ),
                usn
        );

        return result.stream().findFirst();
    }

    // -----------------------
    // SAVE (INSERT + UPDATE)
    // -----------------------
    @Override
    public void save(Student student) {

        String sql = """
                INSERT INTO students (usn,name,cgpa,sem)
                VALUES (?,?,?,?)
                ON CONFLICT (usn)
                DO UPDATE SET
                    name = EXCLUDED.name,
                    cgpa = EXCLUDED.cgpa,
                    sem = EXCLUDED.sem,
                    updated_at = CURRENT_TIMESTAMP
                """;

        jdbcTemplate.update(
                sql,
                student.getUSN(),
                student.getName(),
                student.getCgpa(),
                student.getSem()
        );
    }

    // -----------------------
    // DELETE
    // -----------------------
    @Override
    public int deleteByUsn(String usn) {

        String sql = "DELETE FROM students WHERE usn = ?";
        return jdbcTemplate.update(sql, usn);
    }

    // -----------------------
    // FIND ALL
    // -----------------------
    @Override
    public List<Student> findAll() {

        String sql = "SELECT usn,name,sem,cgpa FROM students";

        return jdbcTemplate.query(
                sql,
                (rs, rowNum) -> new Student(
                        rs.getString("usn"),
                        rs.getString("name"),
                        rs.getInt("sem"),
                        rs.getDouble("cgpa")
                )
        );
    }

    // -----------------------
    // FIND BY NAME
    // -----------------------
    @Override
    public List<Student> findByName(String name) {

        String sql = "SELECT usn,name,sem,cgpa FROM students WHERE LOWER(name) LIKE LOWER(?)";

        return jdbcTemplate.query(
                sql,
                (rs, rowNum) -> new Student(
                        rs.getString("usn"),
                        rs.getString("name"),
                        rs.getInt("sem"),
                        rs.getDouble("cgpa")
                ),
                "%" + name + "%"
        );
    }

    // -----------------------
    // FIND BY SEM
    // -----------------------
    @Override
    public List<Student> findBySemester(int sem) {

        String sql = "SELECT usn,name,sem,cgpa FROM students WHERE sem = ?";

        return jdbcTemplate.query(
                sql,
                (rs, rowNum) -> new Student(
                        rs.getString("usn"),
                        rs.getString("name"),
                        rs.getInt("sem"),
                        rs.getDouble("cgpa")
                ),
                sem
        );
    }

    // -----------------------
    // AUDIT
    // -----------------------
    @Override
    public void insertAudit(String entityName,
                            String entityId,
                            String operation,
                            String oldValue,
                            String newValue) {

        String sql = """
                INSERT INTO audit_logs
                (entity_name, entity_id, operation, old_value, new_value)
                VALUES (?, ?, ?, ?, ?)
                """;

        jdbcTemplate.update(
                sql,
                entityName,
                entityId,
                operation,
                oldValue,
                newValue
        );
    }
}