package com.example.backend.models;

public class Opcija {
    private String naziv;
    private String kategorijausluge;
    private double cena;
    public Opcija(String naziv, String kategorijausluge, double cena) {
        this.naziv = naziv;
        this.kategorijausluge = kategorijausluge;
        this.cena = cena;
    }
    public String getNaziv() {
        return naziv;
    }
    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
    public String getKategorijausluge() {
        return kategorijausluge;
    }
    public void setKategorijausluge(String kategorijausluge) {
        this.kategorijausluge = kategorijausluge;
    }
    public double getCena() {
        return cena;
    }
    public void setCena(double cena) {
        this.cena = cena;
    }

    
}
