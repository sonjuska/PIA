package com.example.backend.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.db.dao.Repo;
import com.example.backend.models.aktivnost;
import com.example.backend.models.korisnik;
import com.example.backend.models.prijava;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;



@RestController
@CrossOrigin(origins = "http://localhost:4200")

public class Controller {

    @PostMapping("/login")
    public korisnik login(@RequestBody korisnik entity) {
        
        return new Repo().login(entity);
    }
    
    @GetMapping("/student/aktuelneAktivnosti")
    public List<aktivnost> aktuelneAktivnosti() {
        return new Repo().aktuelneAktivnosti();
    }
    @GetMapping("/student/aktivnost")
    public aktivnost dohvatiAktivnostPoId(@RequestParam String id) {
        return new Repo().dohvatiAktivnostPoId(id);
    }

    @GetMapping("/student/prijava")
    public prijava dohvatiPrijavu(@RequestParam String korime, String id){
        return new Repo().dohvatiPrijavu(korime,id);
    }

    @GetMapping("/student/prijave")
    public List<prijava> svePrijaveStudenta(@RequestParam String korime) {
        return new Repo().svePrijaveStudenta(korime);
    }

    @GetMapping("/student/novaPrijava")
    public int updateAktivnostiInsertPrijave(@RequestParam String sala, @RequestParam int id, @RequestParam String korime) {
        return new Repo().novaPrijava(sala,id,korime);
    }
    @GetMapping("/nastavnik/aktivnosti")
    public List<aktivnost> nastavnikAktivnosti(@RequestParam String korime) {
        return new Repo().nastavnikAktivnosti(korime);
    }
    @GetMapping("/nastavnik/promeniStatus")
    public int promeniStatus(@RequestParam int status, @RequestParam int id) {
        return new Repo().promeniStatus(status, id);
    }
    @GetMapping("/nastavnik/dodajAktivnost")
    public int dodajAktivnost(@RequestParam String naziv, @RequestParam String datumVreme, @RequestParam String napravio, 
    @RequestParam Boolean sala1, @RequestParam Boolean sala2, @RequestParam Boolean sala3) {
        return new Repo().dodajAktivnost(naziv, datumVreme, napravio, sala1, sala2, sala3);
    }
    
    
     

}
