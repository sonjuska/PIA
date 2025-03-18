package com.example.backend.models;

public class korisnik {
    private String korime;
    private String lozinka;
    private String ime;
    private String prezime;
    private String tip;

    
    public korisnik(String korime, String lozinka, String ime, String prezime, String tip) {
        this.korime = korime;
        this.lozinka = lozinka;
        this.ime = ime;
        this.prezime = prezime;
        this.tip = tip;
    }
    
    public String getKorime() {
        return korime;
    }
    public void setKorime(String korime) {
        this.korime = korime;
    }
    public String getLozinka() {
        return lozinka;
    }
    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }
    public String getIme() {
        return ime;
    }
    public void setIme(String ime) {
        this.ime = ime;
    }
    public String getPrezime() {
        return prezime;
    }
    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }
    public String getTip() {
        return tip;
    }
    public void setTip(String tip) {
        this.tip = tip;
    }

    
}
