package service;

import model.Student;
import java.util.Collection;
import java.util.List;

public interface StudentServiceInterface {

    void addStudent(Student student);

    Student getStudent(String usn);

    Student removeStudent(String usn);

    Collection<Student> getAllStudents();

    List<Student> findStudentsByName(String name);

    void updateCgpa(String usn, double cgpa);

    void updateName(String usn, String name);

    void updateSem(String usn, int sem);

    Collection<Student> getStudentBySemester(int sem);
}
