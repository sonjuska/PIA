package com.example.backend.models;

import java.sql.Date;

public class Zahtev {
    private int idZ;
    private String klijent;
    private int knjiga;
    private String naziv;
    private String[] opcije;
    private double racun;
    private Date datum;
    private String status;

    public Zahtev(int idZ, String klijent, int knjiga, String naziv, String[] opcije, double racun, Date datum,
            String status) {
        this.idZ = idZ;
        this.klijent = klijent;
        this.knjiga = knjiga;
        this.naziv = naziv;
        this.opcije = opcije;
        this.racun = racun;
        this.datum = datum;
        this.status = status;
    }

    public int getIdZ() {
        return idZ;
    }

    public void setIdZ(int idZ) {
        this.idZ = idZ;
    }

    public String getKlijent() {
        return klijent;
    }

    public void setKlijent(String klijent) {
        this.klijent = klijent;
    }

    public int getKnjiga() {
        return knjiga;
    }

    public void setKnjiga(int knjiga) {
        this.knjiga = knjiga;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String[] getOpcije() {
        return opcije;
    }

    public void setOpcije(String[] opcije) {
        this.opcije = opcije;
    }

    public double getRacun() {
        return racun;
    }

    public void setRacun(double racun) {
        this.racun = racun;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    

    
}
