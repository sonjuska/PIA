package com.example.backend.db.models;

public class Agencija {
    private String korisnickoime;
    private String lozinka;
    private String naziv;
    private Long PIB;

    public Agencija(String korisnickoime, String lozinka, String naziv, Long PIB) {
        this.korisnickoime = korisnickoime;
        this.lozinka = lozinka;
        this.naziv = naziv;
        this.PIB = PIB;
    }

    public String getKorisnickoime() {
        return korisnickoime;
    }

    public void setKorisnickoime(String korisnickoime) {
        this.korisnickoime = korisnickoime;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Long getPIB() {
        return PIB;
    }

    public void setPIB(Long PIB) {
        this.PIB = PIB;
    }

    
    
}
