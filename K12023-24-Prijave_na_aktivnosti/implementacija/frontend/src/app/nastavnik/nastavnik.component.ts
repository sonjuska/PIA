import { Component, inject, OnInit } from '@angular/core';
import { korisnik } from '../models/korisnik';
import { Router } from '@angular/router';
import { aktivnost } from '../models/aktivnost';
import { Prijava } from '../models/prijava';
import { ServisService } from '../servis.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-nastavnik',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './nastavnik.component.html',
  styleUrl: './nastavnik.component.css'
})
export class NastavnikComponent implements OnInit{

ruter = inject(Router)
servis = inject(ServisService)

korisnik: korisnik = new korisnik()
aktivnosti: aktivnost[] = []

poruka = ""
naziv="";
datumVreme="";
sala1=false;
sala2=false;
sala3=false;

  ngOnInit(): void {
    
    this.poruka = ""
    let k = localStorage.getItem("loggedUser");

    if(k){
      this.korisnik = JSON.parse(k);
    }

    this.servis.aktivnostiNastavnika(this.korisnik.korime).subscribe(data=>{
      if(data==null){
        this.poruka = "Greska."
      }else{
        this.aktivnosti = data;
        if(this.aktivnosti.length==0){
          this.poruka = "Nema napravljenih aktinvosti."
        }
      }

    })

    
  }
    
  odjava() {
    localStorage.removeItem("loggedUser")
    this.ruter.navigate([""])
  }

  status(s: number){
    if(s==0) return "orange"
    else return ""
  }
  promeniStatus(s: number, id: number) {
    let st=(s==1)? 0: 1;
    this.servis.promeniStatus(st,id).subscribe(data=>{
      if(data){
        this.servis.aktivnostiNastavnika(this.korisnik.korime).subscribe(data=>{
          if(data==null){
            this.poruka = "Greska."
          }else{
            this.aktivnosti = data;
            if(this.aktivnosti.length==0){
              this.poruka = "Nema napravljenih aktinvosti."
            }
          }
    
        })
    
      }
    })
  }

  dodaj() {
    if(this.naziv==""){
      this.poruka="Niste uneli naziv."
    }else if(this.datumVreme==""){
      this.poruka="Niste uneli datum i vreme odrzavanja."
    }else if(this.sala1==false && this.sala2==false && this.sala3==false){
      this.poruka="Unesite bar jednu salu."
    }else{
      this.servis.dodajAktivnost(this.naziv, this.datumVreme, this.korisnik.korime, this.sala1, this.sala2, this.sala3).subscribe(data=>{
        if(data>0){
          this.poruka="Aktivnost uspesno dodata."
          this.servis.aktivnostiNastavnika(this.korisnik.korime).subscribe(data=>{
            if(data){
              this.aktivnosti=data
            }
          })
        }else{
          this.poruka="Greska pri dodavanju aktivnosti."
        }
      })
    }

  }
    


}
