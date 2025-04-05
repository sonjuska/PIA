import { Component, Inject, inject, OnInit } from '@angular/core';
import { Putnik } from '../models/putnik';
import { CommonModule } from '@angular/common';
import { ServisService } from '../servis.service';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { Usluga } from '../models/usluga';
import { KupljeneUslugeAgencija } from '../models/kupljeneUslugeAgencija';

@Component({
  selector: 'app-putnik',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './putnik.component.html',
  styleUrl: './putnik.component.css'
})
export class PutnikComponent implements OnInit{

  ngOnInit(): void {
    let k = localStorage.getItem("loggedUser")
    if(k){
      this.korisnik = JSON.parse(k);
      this.servis.getPutnik(this.korisnik.korisnickoime, this.korisnik.lozinka).subscribe(data=>{
        if(data){
          this.korisnik = data
        }
      })
    }

    this.servis.sveLokacije(this.korisnik.korisnickoime).subscribe(data=>{
      console.log(data)
      if(data){
        console.log(data)
        this.lokacije = data
      }else{
        this.message = "Nijedna lokacija nije posecena."
      }
    })

    this.servis.trenutnaLokacija(this.korisnik.korisnickoime).subscribe(data=>{
      console.log(data)
      if(data){
        this.trenutna_lokacija = data
      }
    })

    this.servis.dohvatiKupljeneUsluge(this.korisnik.korisnickoime).subscribe(data=>{
      if(data){
        this.kupljene = data
      }else{
        this.message = "Nije kupljena nijedna usluga."
      }
    })

    this.servis.pretrazi().subscribe(data=>{
      if(data){
        this.usluge = data
        this.filtriraneUsluge = data
      }
    })
  }

  servis = inject(ServisService)
  ruter = inject(Router)

  korisnik: Putnik = new Putnik()
  lokacije: string [] = []
  trenutna_lokacija=""
  kupljene: KupljeneUslugeAgencija[] = []
  usluge: Usluga[] = []
  filtriraneUsluge: Usluga[] = []
  message = ""
  filterDestinacija=""
  filterTip = ""
  od = 0
  do = NaN


  logout() {
    localStorage.removeItem("loggedUser")
    this.ruter.navigate(['/'])
  }
  promeniTrenutnu(){
    this.servis.promeniTrenutnu(this.korisnik.korisnickoime, this.trenutna_lokacija).subscribe(data=>{
      if(data>0){
        console.log("Uspesno promenjena trenutna lokacija.")
      }
      this.servis.trenutnaLokacija(this.korisnik.korisnickoime).subscribe(data=>{
        if(data){
          this.trenutna_lokacija = data
        }
      })
    })
  }

  pretrazi(){
    this.filtriraneUsluge = []
    this.message=""
    this.servis.pretrazi().subscribe(data=>{
      if(data){

        this.usluge = data
        this.filtriraneUsluge = this.usluge.filter(u=>{
          if(this.filterDestinacija){
            return u.lokacija_do.includes(this.filterDestinacija)
          }else{
            return true
          }
        }).filter(u=>{
          if(this.filterTip){
            return u.tip.includes(this.filterTip)
          }else{
            return true
          }
        }).filter(u=>{
          if(this.do>0){
            return u.cena >= this.od && u.cena <= this.do;
          }else if(this.do==0 || Number.isNaN(this.do)){
            return u.cena >= this.od
          }else{
            return true
          }
        })

        if(this.filtriraneUsluge.length==0){
          this.message = "Nema rezultata koji zadovoljavaju pretragu."
        }

      }
    })

  }

  kupi(id: number) {
   this.ruter.navigate([`putnik/${id}`])
  }

}
