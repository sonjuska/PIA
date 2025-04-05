package com.example.backend.db.models;

public class Putnik {
    private String korisnickoime;
    private String lozinka;
    private String ime_prezime;
    private String imejl;
    private String lokacija_trenutna;
    private double novac;

    public Putnik(String korisnickoime, String lozinka, String ime_prezime, String imejl, String lokacija_trenutna,
            double novac) {
        this.korisnickoime = korisnickoime;
        this.lozinka = lozinka;
        this.ime_prezime = ime_prezime;
        this.imejl = imejl;
        this.lokacija_trenutna = lokacija_trenutna;
        this.novac = novac;
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

    public String getIme_prezime() {
        return ime_prezime;
    }

    public void setIme_prezime(String ime_prezime) {
        this.ime_prezime = ime_prezime;
    }

    public String getImejl() {
        return imejl;
    }

    public void setImejl(String imejl) {
        this.imejl = imejl;
    }

    public String getLokacija_trenutna() {
        return lokacija_trenutna;
    }

    public void setLokacija_trenutna(String lokacija_trenutna) {
        this.lokacija_trenutna = lokacija_trenutna;
    }

    public double getNovac() {
        return novac;
    }

    public void setNovac(double novac) {
        this.novac = novac;
    }

    

    
}
