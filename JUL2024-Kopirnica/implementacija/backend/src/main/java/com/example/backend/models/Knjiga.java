package com.example.backend.models;

public class Knjiga {
    private int idK;
    private String klijent;
    private String naziv;
    private int strane;
    public Knjiga(int idK, String klijent, String naziv, int strane) {
        this.idK = idK;
        this.klijent = klijent;
        this.naziv = naziv;
        this.strane = strane;
    }
    public int getIdK() {
        return idK;
    }
    public void setIdK(int idK) {
        this.idK = idK;
    }
    public String getKlijent() {
        return klijent;
    }
    public void setKlijent(String klijent) {
        this.klijent = klijent;
    }
    public String getNaziv() {
        return naziv;
    }
    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
    public int getStrane() {
        return strane;
    }
    public void setStrane(int strane) {
        this.strane = strane;
    }

    
}
