package repository;
import model.Student;

import java.util.Collection;
import java.util.List;

public interface StudentRepository {
    List<Student> loadStudents();
    void saveStudents(Collection<Student> Students);
}
