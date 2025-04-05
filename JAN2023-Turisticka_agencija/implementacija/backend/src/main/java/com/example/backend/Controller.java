package com.example.backend;

import org.springframework.web.bind.annotation.RestController;

import com.example.backend.db.dao.Repo;
import com.example.backend.db.models.Agencija;
import com.example.backend.db.models.KupljeneUslugeAgencija;
import com.example.backend.db.models.Putnik;
import com.example.backend.db.models.Usluga;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class Controller {

    @PostMapping("/loginPutnik")
    public Putnik login(@RequestBody Putnik entity) {
        return new Repo().loginPutnik(entity);
    }

    @PostMapping("/loginAgencija")
    public Agencija login(@RequestBody Agencija entity) {
        return new Repo().loginAgencija(entity);
    }

    @GetMapping("/uslugePoIdA")
    public List<Usluga> uslugePoIdA(@RequestParam String korisnickoime){
        return new Repo().uslugePoIdA(korisnickoime);
    }

    @GetMapping("/dodajUslugu")
    public int dodajUslugu(@RequestParam String idAgencije, @RequestParam String tip, @RequestParam String lokacija_od, @RequestParam String lokacija_do, @RequestParam String period, @RequestParam double cena, @RequestParam int broj_mesta){

        return new Repo().dodajUslugu(idAgencije, tip, lokacija_od, lokacija_do, period, cena, broj_mesta);
    }

    @GetMapping("/sveLokacije")
    public List<String> sveLokacije(@RequestParam String id_putnik){
        return new Repo().sveLokacije(id_putnik);
    }
    @GetMapping("/trenutnaLokacija")
    public String trenutnaLokacija(@RequestParam String id_putnik){
        return new Repo().trenutnaLokacija(id_putnik);
    }

    @GetMapping("/promeniTrenutnuLokaciju")
    public int promeniTrenutnuLokaciju(@RequestParam String id_putnik, @RequestParam String lokacija){
        return new Repo().promeniTrenutnuLokaciju(id_putnik, lokacija);
    }

    @GetMapping("/dohvatiKupljeneUsluge")
    public List<KupljeneUslugeAgencija> dohvatiKupljeneUsluge(@RequestParam String korisnickoime){
        return new Repo().dohvatiKupljeneUsluge(korisnickoime);
    }
    
    @GetMapping("/dohvatiUsluge")
    public List<Usluga> dohvatiUsluge(){
        return new Repo().dohvatiUsluge();
    }

    @GetMapping("/dodajKupljenuUslugu")
    public int dodajKupljenuUslugu(@RequestParam int id_usluge, @RequestParam String id_putnik, @RequestParam int broj_saputnika){
        return new Repo().dodajKupljenuUslugu(id_usluge, id_putnik, broj_saputnika);
    }

    @GetMapping("/getPutnik")
    public Putnik getPutnik(@RequestParam String korisnickoime, @RequestParam String lozinka){
        return new Repo().getPutnik(korisnickoime,lozinka);
    }
    @GetMapping("/getAgencija")
    public Agencija getAgencija(@RequestParam String korisnickoime, @RequestParam String lozinka){
        return new Repo().getAgencija(korisnickoime,lozinka);
    }
}
