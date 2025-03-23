package com.example.backend;

import org.springframework.web.bind.annotation.RestController;

import com.example.backend.db.dao.Repo;
import com.example.backend.models.Aukcija;
import com.example.backend.models.Umetnina;
import com.example.backend.models.User;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class Controller {

    @PostMapping("/login")
    public User login(@RequestBody User entity) {
        return new Repo().login(entity);
    }

    @GetMapping("/aktivneAukcije")
    public List<Aukcija> dohvatiAktivneAukcije() {
        return new Repo().aktivneAukcije();
    }

    @GetMapping("/sveAukcije")
    public List<Aukcija> dohvatiSveAukcije() {
        return new Repo().sveAukcije();
    }
    
    @GetMapping("/neaktivneAukcije")
    public List<Aukcija> dohvatiNeaktivneAukcije() {
        return new Repo().neaktivneAukcije();
    }

    @GetMapping("/aukcija/{idA}")
    public List<Umetnina> umetninePoIdA(@PathVariable String idA) {
        return new Repo().umetninePoIdA(idA);
    }

    @PostMapping("/ponudi")
    public int updatePonuda(@RequestBody Umetnina u) {
        return new Repo().updatePonuda((int)u.getIdU(), (int)u.getIdA(), (String)u.getNaziv(), (int)u.getPonuda(), (String)u.getVlasnik());
    }

    /* 
    @GetMapping("/maksUmetninaId")
    public int maksUmetninaId(){
        return new UserRepo().maksUmetninaId();
    }
    */

    @GetMapping("/kupljeneUmetnine")
    public List<Umetnina> kupljeneUmetnine(@RequestParam String username) {
        return new Repo().kupljeneUmetnine(username);
    }
    
    @GetMapping("/sveKupljeneUmetnine")
    public List<Umetnina> sveKupljeneUmetnine() {
        return new Repo().sveKupljeneUmetnine();
    }
    
    
    
}
