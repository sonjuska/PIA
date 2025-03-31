package com.example.backend.models;

public class Korisnik {
    private String kor_ime;
    private String lozinka;
    private String ime;
    private String prezime;
    private String mejl;
    private String tip;
    public Korisnik(String kor_ime, String lozinka, String ime, String prezime, String mejl, String tip) {
        this.kor_ime = kor_ime;
        this.lozinka = lozinka;
        this.ime = ime;
        this.prezime = prezime;
        this.mejl = mejl;
        this.tip = tip;
    }
    public String getKor_ime() {
        return kor_ime;
    }
    public void setKor_ime(String kor_ime) {
        this.kor_ime = kor_ime;
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
    public String getMejl() {
        return mejl;
    }
    public void setMejl(String mejl) {
        this.mejl = mejl;
    }
    public String getTip() {
        return tip;
    }
    public void setTip(String tip) {
        this.tip = tip;
    }

    
}
