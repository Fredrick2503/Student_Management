package com.students.model;
import java.util.regex.Pattern;


public class Student {
    private String usn;
    private String name;
    private double cgpa;
    private int sem;
    private static final Pattern USN_PATTERN = Pattern.compile("^[0-9]{1}[A-Z]{2}[0-9]{2}[A-Z]{2}[0-9]{3}$");
    public Student(){}
    public Student(String usn, String name, int sem, double cgpa) {
        this.usn=validateUsn(usn);
        setName(name);
        setCgpa(cgpa);
        setSem(sem);
    }

    private String validateUsn(String usn){
        if(usn == null) {
            throw new IllegalArgumentException("Illegal USN");
        }
        usn = usn.trim().toUpperCase();
        if (!USN_PATTERN.matcher(usn).matches()) {
            throw new IllegalArgumentException("Invalid USN Format");
        }
        return usn.toUpperCase();
    }

    public String getUSN() {
        return this.usn;
    }

    public String getName() {
        return this.name;
    }

    public double getCgpa() {
        return this.cgpa;
    }

    public int getSem() {
        return this.sem;
    }

    public void setName(String name) {
        if (name != null) {
            name = name.trim();
            if (!name.isBlank()) {
                this.name = name;
                return;
            }
        }
        throw new IllegalArgumentException("Illegal Name argument");
    }

    public void setCgpa(double cgpa) {
        if (cgpa >= 0 && cgpa <= 10)
            this.cgpa = cgpa;
        else
            throw new IllegalArgumentException("Illegal cgpa argument");
    }

    public void setSem(int sem) {
        if (sem > 0 && sem <= 8)
            this.sem = sem;
        else
            throw  new IllegalArgumentException("Illegal sem arguememnt");
    }

    @Override
    public String toString() {
        return this.usn + "|" + this.name + "|" + this.cgpa + "|" + this.sem;
    }
}
