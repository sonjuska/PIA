package com.example.backend.models;

public class aktivnost {
    private int id;
    private String naziv;
    private String datum_vreme;
    private String napravio;
    private int status;
    private int sala1;
    private int sala2;
    private int sala3;

    public aktivnost(int id, String naziv, String datum_vreme, String napravio, int status, int sala1, int sala2, int sala3) {
        this.id = id;
        this.naziv = naziv;
        this.datum_vreme = datum_vreme;
        this.napravio = napravio;
        this.status = status;
        this.sala1 = sala1;
        this.sala2 = sala2;
        this.sala3 = sala3;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getDatum_vreme() {
        return datum_vreme;
    }

    public void setDatum_vreme(String datum_vreme) {
        this.datum_vreme = datum_vreme;
    }

    public String getNapravio() {
        return napravio;
    }

    public void setNapravio(String napravio) {
        this.napravio = napravio;
    }

    public int getSala1() {
        return sala1;
    }

    public void setSala(int sala1) {
        this.sala1 = sala1;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setSala1(int sala1) {
        this.sala1 = sala1;
    }

    public int getSala2() {
        return sala2;
    }

    public void setSala2(int sala2) {
        this.sala2 = sala2;
    }

    public int getSala3() {
        return sala3;
    }

    public void setSala3(int sala3) {
        this.sala3 = sala3;
    }
    

    
    
    
}
