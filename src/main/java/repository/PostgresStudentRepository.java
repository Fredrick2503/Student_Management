package repository;

import config.DatabaseConfig;
import model.Student;

import java.sql.*;
import java.util.*;

public class PostgresStudentRepository implements StudentRepository {
//    @Override
//    public List<Student> loadStudents(){
//        return new ArrayList<>();
//    }
//
//    public void saveStudents(Collection<Student> Students){
//        return;
//    }
    @Override
    public Optional<Student> findByUsn(Connection conn,String usn){
        String sql = "SELECT usn,name,cgpa,sem FROM students WHERE usn = ?";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1,usn);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    Student student = new Student(
                            rs.getString("usn"),
                            rs.getString("name"),
                            rs.getInt("sem"),
                            rs.getDouble("cgpa")
                    );
                    return Optional.of(student);
                }
            }
        }catch (SQLException e){
            throw new RuntimeException("Database error", e);
        }
        return Optional.empty();
    }
    @Override
    public void save(Connection conn,Student student){
        String sql = """
                INSERT INTO students (usn,name,cgpa,sem)
                VALUES (?,?,?,?)
                ON CONFLICT (usn)
                DO UPDATE SET
                    name = EXCLUDED.name,
                    cgpa = EXCLUDED.cgpa,
                    sem = EXCLUDED.sem
                """;
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1,student.getUSN());
            ps.setString(2,student.getName());
            ps.setDouble(3,student.getCgpa());
            ps.setInt(4,student.getSem());
            ps.executeUpdate();
        }catch(SQLException e){
            throw new RuntimeException("Database error",e);
        }
    }
    public void deleteByUsn(Connection conn,String usn){
        String sql = """
                DELETE FROM students WHERE usn = ?
                """;
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1,usn);
            ps.executeUpdate();
       }catch (SQLException e){
            throw new RuntimeException("Database error: ",e);
        }

    }

    @Override
    public List<Student> findAll(Connection conn) {
        String sql = """
                SELECT usn,name,cgpa,sem FROM students
                """ ;
        List<Student> students = new ArrayList<>();
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    Student student = new Student(
                            rs.getString("usn"),
                            rs.getString("name"),
                            rs.getInt("sem"),
                            rs.getDouble("cgpa")
                            );
                    students.add(student);
                }
                return students;
            }
        }catch (SQLException e){
            throw new RuntimeException("Database error: ",e);
        }
    }

    @Override
    public List<Student> findByName(Connection conn,String name) {
        String sql = """
                SELECT usn,name,cgpa,sem FROM students WHERE LOWER(name) LIKE LOWER(?)
                """ ;
        List<Student> students = new ArrayList<>();
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1,"%"+name+"%");
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    Student student = new Student(
                            rs.getString("usn"),
                            rs.getString("name"),
                            rs.getInt("sem"),
                            rs.getDouble("cgpa")
                    );
                    students.add(student);
                }
                return students;
            }
        }catch (SQLException e){
            throw new RuntimeException("Database error: ",e);
        }
    }

    @Override
    public List<Student> findBySemester(Connection conn,int sem) {
        String sql = """
                SELECT usn,name,cgpa,sem FROM students WHERE sem = ?
                """ ;
        List<Student> students = new ArrayList<>();
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1,sem);
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    Student student = new Student(
                            rs.getString("usn"),
                            rs.getString("name"),
                            rs.getInt("sem"),
                            rs.getDouble("cgpa")
                    );
                    students.add(student);
                }
                return students;
            }
        }catch (SQLException e){
            throw new RuntimeException("Database error: ",e);
        }
    }

    @Override
    public void insertAudit(Connection conn,String usn,String action){
        String insetAudit = """
                INSERT INTO student_audit (usn,action)
                VALUES (?,?)
                """;
        try(PreparedStatement psAudit = conn.prepareStatement(insetAudit)){
                psAudit.setString(1,usn);
                psAudit.setString(2,"SAVE");
                psAudit.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException("Database error: ",e);
        }
    }
}
