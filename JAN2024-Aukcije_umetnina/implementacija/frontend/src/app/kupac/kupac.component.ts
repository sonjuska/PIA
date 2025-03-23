import { Component, inject, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Aukcija } from '../models/aukcija';
import { ServisService} from '../servis.service';
import { Korisnik } from '../models/korisnik';
import { Umetnina } from '../models/umetnina';


@Component({
  selector: 'app-kupac',
  standalone: true,
  imports: [],
  templateUrl: './kupac.component.html',
  styleUrl: './kupac.component.css'
})
export class KupacComponent implements OnInit{

  ngOnInit(): void {
    let k = localStorage.getItem("loggedUser")
    if(k)
      this.korisnik = JSON.parse(k);
    else
      this.message = "Nema ulogovanog korisnika. Interna greska."

    this.servis.dohvatiAktivneAukcije().subscribe((data)=>{
      if(!data){
        this.message = "Greska"
      }else{
        this.aktivneAukcije = data
      }
    })
    this.servis.dohvatiKupljeneUmetnine(this.korisnik.username).subscribe((data)=>{
      if(!data){
        this.message="Greska"
      }else{
        this.kupljeneUmetnine = data
      }
    })
  }

  ruter = inject(Router)
  servis = inject(ServisService)

  korisnik: Korisnik = new Korisnik()
  message = ""
  aktivneAukcije: Aukcija [] = []
  kupljeneUmetnine: Umetnina [] = []

  logout() {
    localStorage.removeItem("loggedUser")
    this.ruter.navigate(["/"])
  }

  detalji(idA: number) {
    this.ruter.navigate([`/aukcija/${idA.toString()}`]);
  }

}
