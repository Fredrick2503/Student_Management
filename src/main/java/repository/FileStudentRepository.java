package repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Student;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileStudentRepository{
    private static final Logger logger = LoggerFactory.getLogger(FileStudentRepository.class);

    private static final String FILE_PATH = "students.json";

    private static final ObjectMapper mapper = new ObjectMapper();

    public List<Student> loadStudents() {
        try {
            logger.info("Loading students from storage...");
            File file = new File(FILE_PATH);

            if (!file.exists()) {
                logger.warn("Student file not found. Starting with empty storage");
                return new ArrayList<>();
            }
            List<Student> list= mapper.readValue(
                    file,
                    new TypeReference<List<Student>>() {}
            );
            logger.info("Loaded Students from file size: {}", list.size());
            return list;

        } catch (IOException e) {
            throw new RuntimeException("Failed to load students", e);
        }
    }

    public void saveStudents(Collection<Student> students) {
        try {
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File(FILE_PATH), students);
            logger.debug("Persisted {} students to storage",students.size());
        } catch (IOException e) {
            logger.error("Failed to save students");
            throw new RuntimeException("Failed to save students", e);
        }
    }
}
