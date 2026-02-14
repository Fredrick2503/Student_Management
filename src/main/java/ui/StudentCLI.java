package ui;

import service.StudentServiceInterface;
import model.Student;

import java.util.Scanner;

public class StudentCLI {

    private final StudentServiceInterface service;
    private final Scanner scanner;

    public StudentCLI(StudentServiceInterface service) {
        this.service = service;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            printMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewAll();
                case 3 -> removeStudent();
                case 0 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void printMenu() {
        System.out.println("""
                1. Add Student
                2. View All Students
                3. Remove Student
                0. Exit
                """);
    }

    private void addStudent() {
        try {
            System.out.print("USN: ");
            String usn = scanner.nextLine();

            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Semester: ");
            int sem = Integer.parseInt(scanner.nextLine());

            System.out.print("CGPA: ");
            double cgpa = Double.parseDouble(scanner.nextLine());

            service.addStudent(new Student(usn, name, sem, cgpa));

            System.out.println("Student added successfully.");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void viewAll() {
        for (Student s : service.getAllStudents()) {
            System.out.println(s);
        }
    }

    private void removeStudent() {
        try {
            System.out.print("Enter USN to remove: ");
            String usn = scanner.nextLine();
            service.removeStudent(usn);
            System.out.println("Student removed.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
