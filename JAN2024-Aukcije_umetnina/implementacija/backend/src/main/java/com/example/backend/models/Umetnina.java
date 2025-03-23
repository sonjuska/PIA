package com.example.backend.models;

public class Umetnina {

    private int idU;
    private int idA;
    private String naziv;
    private int ponuda;
    private String vlasnik;

    public Umetnina(int idU, int idA, String naziv, int ponuda, String vlasnik) {
        
        this.idU = idU;
        this.idA = idA;
        this.naziv = naziv;
        this.ponuda = ponuda;
        this.vlasnik = vlasnik;
    }

    public int getIdA() {
        return idA;
    }

    public void setIdA(int idA) {
        this.idA = idA;
    }

    public int getIdU() {
        return idU;
    }

    public void setIdU(int idU) {
        this.idU = idU;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getPonuda() {
        return ponuda;
    }

    public void setPonuda(int ponuda) {
        this.ponuda = ponuda;
    }

    public String getVlasnik() {
        return vlasnik;
    }

    public void setVlasnik(String vlasnik) {
        this.vlasnik = vlasnik;
    }

    
    
}
