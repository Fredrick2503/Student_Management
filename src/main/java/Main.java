import config.TransactionManager;
import repository.FileStudentRepository;
import repository.PostgresStudentRepository;
import repository.StudentRepository;
import service.StudentService;
import service.StudentServiceInterface;
import ui.StudentCLI;
import config.DatabaseConfig;
import java.sql.Connection;
import model.Student;

public class Main {
        public static void main(String[] args) {

        StudentRepository repository = new PostgresStudentRepository();
//        StudentRepository repository = new FileStudentRepository();

        StudentServiceInterface service = new StudentService(repository);

        StudentCLI cli = new StudentCLI(service);

        cli.start();
    }
//    public static void main(String[] args) {
//
//        StudentRepository repo = new PostgresStudentRepository();
//
//
//        Student s = new Student("1RV23CS112", "Alice", 5, 8.7);
//        TransactionManager trans = new TransactionManager();
//        trans.execute(conn -> {
//            repo.save(conn,s);
//            repo.insertAudit(conn, s.getUSN(),"Saving Student");
//        });
//        repo.save(s);
//
//
//        System.out.println(repo.findByUsn("1RV23CS002"));
//
//    }
}
