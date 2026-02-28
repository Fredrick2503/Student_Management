package com.students.exception;

public class StudentNotFoundException extends RuntimeException{
    public StudentNotFoundException(String usn){
        super("No student found with USN: "+usn);
    }
}
