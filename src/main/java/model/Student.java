package model;

public class Student {
    private String usn;
    private String name;
    private double cgpa;
    private int sem;
    public Student(){}
    public Student(String usn, String name, int sem, double cgpa) {
        this.usn=validateUsn(usn);
        setName(name);
        setCgpa(cgpa);
        setSem(sem);
    }

    private String validateUsn(String usn){
        if(usn == null) {
            throw new IllegalArgumentException("Ilegal USN");
        }
        usn = usn.trim();
        if (usn.isBlank()) {
            throw new IllegalArgumentException("Ilegal USN");
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
