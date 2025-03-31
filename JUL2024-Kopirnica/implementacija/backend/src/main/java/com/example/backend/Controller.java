package com.example.backend;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.db.dao.Repo;
import com.example.backend.models.Knjiga;
import com.example.backend.models.Korisnik;
import com.example.backend.models.Opcija;
import com.example.backend.models.Zahtev;
import com.example.backend.models.ZahtevJoinKlijent;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@CrossOrigin(origins = "http://localhost:4200/")

public class Controller {
    @PostMapping("/login")
    public Korisnik login(@RequestBody Korisnik entity) {
        return new Repo().login(entity);
    }

    @GetMapping("/knjigeKlijent")
    public List<Knjiga> knjigeKlijent(@RequestParam String kor_ime) {
        return new Repo().knjigeKlijent(kor_ime);
    }

    @GetMapping("knjigaDetalji")
    public Knjiga knjigaDetalji(@RequestParam int idK) {
        return new Repo().knjigaDetalji(idK);
    }
    @GetMapping("azurirajKnjigu")
    public int azurirajKnjigu(@RequestParam String naziv, @RequestParam int strane, @RequestParam int idK) {
        return new Repo().azurirajKnjigu(naziv,strane,idK);
    }
    @GetMapping("zahteviKlijent")
    public List<Zahtev> zahteviKlijent(@RequestParam String kor_ime) {
        return new Repo().zahteviKlijent(kor_ime);
    }
    @GetMapping("zahtev")
    public Zahtev zahtev(@RequestParam int idZ) {
        return new Repo().zahtev(idZ);
    }
    @GetMapping("opcije")
    public List<Opcija> opcije() {
        return new Repo().opcije();
    }
    @GetMapping("azurirajZahtev")
    public int azurirajZahtev(@RequestParam String opcije, @RequestParam int idZ, @RequestParam double racun, @RequestParam int idK) {
        return new Repo().azurirajZahtev(opcije, idZ, racun, idK);
    }
    @GetMapping("noviZahtevi")
    public List<ZahtevJoinKlijent> noviZahtevi() {
        return new Repo().noviZahtevi();
    }
    @GetMapping("obrisiOpciju")
    public int obrisiOpciju(@RequestParam String naziv, @RequestParam String kategorijausluge, @RequestParam double cena) {
        return new Repo().obrisiOpciju(naziv, kategorijausluge, cena);
    }
    @GetMapping("promeniStatusZahteva")
    public int promeniStatusZahteva(@RequestParam int idZ) {
        return new Repo().promeniStatusZahteva(idZ);
    }
    
    
    
    
    
    
    
    
}
