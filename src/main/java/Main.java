import repository.FileStudentRepository;
import repository.StudentRepository;
import service.StudentService;
import service.StudentServiceInterface;
import ui.StudentCLI;

public class Main {
    public static void main(String[] args) {

        StudentRepository repository = new FileStudentRepository();

        StudentServiceInterface service = new StudentService(repository);

        StudentCLI cli = new StudentCLI(service);

        cli.start();
    }
}
