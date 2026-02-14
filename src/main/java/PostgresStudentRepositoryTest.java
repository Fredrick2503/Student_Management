import config.DatabaseConfig;
import model.Student;
import repository.PostgresStudentRepository;
import repository.StudentRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PostgresStudentRepositoryTest {

//    public static void main(String[] args) {
//
//        StudentRepository repo = new PostgresStudentRepository();
//
//        // 1. Save Student
//        Student s1 = new Student("1RV23CS100", "Alice", 5, 8.7);
//        repo.save(s1);
//        System.out.println("Saved Alice");
//
//        // 2. Find By USN
//        Optional<Student> found = repo.findByUsn("1RV23CS100");
//        System.out.println("Find By USN: " + found);
//
//        // 3. Update (Upsert Test)
//        Student updated = new Student("1RV23CS100", "Alice Updated", 6, 9.1);
//        repo.save(updated);
//        System.out.println("Updated Alice");
//
//        Optional<Student> afterUpdate = repo.findByUsn("1RV23CS100");
//        System.out.println("After Update: " + afterUpdate);
//
//        // 4. Insert More
//        repo.save(new Student("1RV23CS101", "Bob", 4, 7.2));
//        repo.save(new Student("1RV23CS102", "Charlie", 5, 8.1));
//
//        // 5. Find All
//        List<Student> all = repo.findAll();
//        System.out.println("All Students:");
//        all.forEach(System.out::println);
//
//        // 6. Find By Name (Partial)
//        List<Student> nameSearch = repo.findByName("Ali");
//        System.out.println("Search By Name (Ali):");
//        nameSearch.forEach(System.out::println);
//
//        // 7. Find By Semester
//        List<Student> semSearch = repo.findBySemester(5);
//        System.out.println("Search By Semester (5):");
//        semSearch.forEach(System.out::println);
//
//        // 8. Delete
//        repo.deleteByUsn("1RV23CS101");
//        System.out.println("Deleted Bob");
//
//        System.out.println("Final List:");
//        repo.findAll().forEach(System.out::println);
//        repo.saveStudentWithAudit(new Student("1RV23CS200", "TestUser", 5, 8.2));
//
//        System.out.println("Test Completed Successfully.");
//    }

}
