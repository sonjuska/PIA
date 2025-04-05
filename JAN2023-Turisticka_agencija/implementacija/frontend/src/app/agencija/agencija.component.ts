import { Component, inject, OnInit } from '@angular/core';
import { Agencija } from '../models/agencija';
import { FormsModule } from '@angular/forms';
import { Usluga } from '../models/usluga';
import { ServisService } from '../servis.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-agencija',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './agencija.component.html',
  styleUrl: './agencija.component.css'
})
export class AgencijaComponent implements OnInit{


  ngOnInit(): void {
    let a = localStorage.getItem("loggedUser")
    if(a){
      let parsed = JSON.parse(a);
      this.servis.getAgencija(parsed.korisnickoime, parsed.lozinka).subscribe(data=>{
        if(data){
          this.agencija=data
          console.log(data)
        }else{
          console.log("Greska pri ucitavanju agencije")
        }
        console.log("Agencija loaded:", this.agencija);

        this.servis.uslugePoId(this.agencija.korisnickoime).subscribe(data=>{
          console.log(data)
          if(data){
            this.usluge = data
          }else{
            this.message = "Trenutno nema usluga ove agencije."
          }
        })
      })
    }
    

    
  }

  
  servis = inject(ServisService)
  ruter = inject(Router)

  usluge: Usluga [] = []
  agencija: Agencija = new Agencija()
  message = ""
  message2=""
  tip = ""
  lokacija_od = ""
  lokacija_do = ""
  period = ""
  cena = 0.0
  broj_mesta=0

  zuta(arg0: number) {
    if(arg0<5){
      return "yellow"
    }
    return ""
  }

  logout() {
    localStorage.removeItem("loggedUser")
    this.ruter.navigate(['/'])
  }

  dodaj(tip: string, lokacija_od: string, lokacija_do: string, period: string, cena: number, broj_mesta: number){
    if(tip==""){
      this.message2 = "Nije popunjeno polje Tip."
    }else if(lokacija_od=="" && tip!="hotel"){
      this.message2 = "Nije popunjeno polje Lokacija od."
    }else if(lokacija_do==""){
      this.message2 = "Nije popunjeno polje Lokacija do."
    }else if(period==""){
      this.message2 = "Nije popunjeno polje Period."
    }else if(cena<=0.0){
      this.message2 = "Polje Cena mora biti pozitivan broj."
    }else if(broj_mesta<=0){
      this.message2 = "Polje Broj mesta mora biti pozitivan broj."
    }else{
      this.servis.dodajUslugu(this.agencija.korisnickoime, tip, lokacija_od, lokacija_do, period, cena, broj_mesta).subscribe(data=>{

        if(data>0){
          console.log("Dodata usluga.")
        }else{
          console.log("greska")
        }
        this.servis.uslugePoId(this.agencija.korisnickoime).subscribe(data=>{
          console.log(data)
          if(data){
            this.usluge = data
          }else{
            this.message = "Trenutno nema usluga ove agencije."
          }
        })
      })
      this.message2 = ""
    }

  }
  boja() {
    if(this.message2!=""){
      return "red"
    }else{
      return ""
    }
  }

}
