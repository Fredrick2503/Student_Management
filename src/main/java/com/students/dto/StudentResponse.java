package com.students.dto;

public class StudentResponse {

    private String usn;
    private String name;
    private int sem;
    private double cgpa;

    public StudentResponse(String usn, String name, int sem, double cgpa) {
        this.usn = usn;
        this.name = name;
        this.sem = sem;
        this.cgpa = cgpa;
    }

    public String getUsn() { return usn; }
    public String getName() { return name; }
    public int getSem() { return sem; }
    public double getCgpa() { return cgpa; }
}