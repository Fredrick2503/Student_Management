package com.students.controller;

import com.students.dto.StudentRequest;
import com.students.dto.StudentResponse;
import com.students.model.Student;
import com.students.service.StudentService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping("/ping")
    public String ping() {
        System.out.println("Hello");
        return "pong";
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody StudentRequest request) {

        Student student = new Student(
                request.getUsn(),
                request.getName(),
                request.getSem(),
                request.getCgpa()
        );

        service.addStudent(student);

        return ResponseEntity.status(201).build();
    }

    // READ ONE
    @GetMapping("/{usn}")
    public StudentResponse get(@PathVariable String usn) {

        Student s = service.getStudent(usn);

        return new StudentResponse(
                s.getUSN(),
                s.getName(),
                s.getSem(),
                s.getCgpa()
        );
    }

    // READ ALL
    @GetMapping("/all")
        public List<StudentResponse> getAll() {
            System.out.println("Hit");
            return service.getAllStudents()
                .stream()
                .map(s -> new StudentResponse(
                        s.getUSN(),
                        s.getName(),
                        s.getSem(),
                        s.getCgpa()
                ))
                .collect(Collectors.toList());
    }

    // UPDATE
    @PutMapping("/{usn}")
    public ResponseEntity<Void> update(
            @PathVariable String usn,
            @Valid @RequestBody StudentRequest request) {

        service.updateStudent(usn, s -> {
            s.setName(request.getName());
            s.setSem(request.getSem());
            s.setCgpa(request.getCgpa());
        });

        return ResponseEntity.ok().build();
    }

    // DELETE
    @DeleteMapping("/{usn}")
    public ResponseEntity<Void> delete(@PathVariable String usn) {

        service.removeStudent(usn);

        return ResponseEntity.noContent().build();
    }
}