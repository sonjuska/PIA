package com.example.backend.models;

public class prijava {
    private String student;
    private int aktivnost;
    private String sala;

    
    public prijava(String student, int aktivnost, String sala) {
        this.student = student;
        this.aktivnost = aktivnost;
        this.sala = sala;
    }
    public String getStudent() {
        return student;
    }
    public void setStudent(String student) {
        this.student = student;
    }
    public int getAktivnost() {
        return aktivnost;
    }
    public void setAktivnost(int aktivnost) {
        this.aktivnost = aktivnost;
    }
    public String getSala() {
        return sala;
    }
    public void setSala(String sala) {
        this.sala = sala;
    }

    

}
