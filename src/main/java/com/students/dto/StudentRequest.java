package com.students.dto;

import jakarta.validation.constraints.*;

public class StudentRequest {

    @NotBlank
    private String usn;

    @NotBlank
    private String name;

    @Min(1)
    @Max(8)
    private int sem;

    @DecimalMin("0.0")
    @DecimalMax("10.0")
    private double cgpa;

    public String getUsn() { return usn; }
    public void setUsn(String usn) { this.usn = usn; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getSem() { return sem; }
    public void setSem(int sem) { this.sem = sem; }

    public double getCgpa() { return cgpa; }
    public void setCgpa(double cgpa) { this.cgpa = cgpa; }
}