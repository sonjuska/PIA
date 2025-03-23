package com.example.backend.models;

public class Aukcija {

    private int idA;
    private String naziv;
    private String pocetak;
    private String kraj;

    public Aukcija(int idA, String naziv, String pocetak, String kraj) {
        this.idA = idA;
        this.naziv = naziv;
        this.pocetak = pocetak;
        this.kraj = kraj;
    }
    
    public int getIdA() {
        return idA;
    }
    public void setIdA(int idA) {
        this.idA = idA;
    }
    public String getNaziv() {
        return naziv;
    }
    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
    public String getPocetak() {
        return pocetak;
    }
    public void setPocetak(String pocetak) {
        this.pocetak = pocetak;
    }
    public String getKraj() {
        return kraj;
    }
    public void setKraj(String kraj) {
        this.kraj = kraj;
    }

    
}
