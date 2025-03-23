import { Component, inject, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Aukcija } from '../models/aukcija';
import { ServisService } from '../servis.service';
import { Umetnina } from '../models/umetnina';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-prodavac',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './prodavac.component.html',
  styleUrl: './prodavac.component.css'
})
export class ProdavacComponent implements OnInit{

  ngOnInit(): void {
    this.servis.dohvatiSveAukcije().subscribe((data)=>{
      if(!data){
        this.message = "Greska"
      }else{
        this.sveAukcije = data
      }
    })

    this.servis.dohvatiNeaktivneAukcije().subscribe((data)=>{
      if(!data){
        this.message = "Greska"
      }else{
        this.istekleAukcije = data
      }
    })
    this.servis.dohvatiSveKupljeneUmetnine().subscribe((data)=>{
      if(!data){
        this.message="Greska"
      }else{
        this.kupljeneUmetnine=data
      }
    })
  }


  ruter = inject(Router)
  servis = inject(ServisService)
  sveAukcije: Aukcija [] = []
  istekleAukcije: Aukcija [] = []
  kupljeneUmetnine: Umetnina []=[];
  message = ""

  logout() {
    localStorage.removeItem("loggedUser")
    this.ruter.navigate(["/"])
  }

  in(a: Aukcija){
    for(let i=0;i<this.istekleAukcije.length;i++){
      if(this.istekleAukcije[i].idA ==a.idA){
        return "red"
      }
    }
    return "green"
  }

}
