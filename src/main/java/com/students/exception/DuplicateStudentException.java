package com.students.exception;


public class DuplicateStudentException extends RuntimeException{

    public DuplicateStudentException(String usn){
        super("No student found with USN: "+usn);
    }
}
