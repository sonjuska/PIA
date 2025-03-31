import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ServisService } from '../servis.service';
import { Knjiga } from '../models/knjiga';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-knjiga-details',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './knjiga-details.component.html',
  styleUrl: './knjiga-details.component.css'
})
export class KnjigaDetailsComponent implements OnInit{

  ngOnInit(): void {
    let id = this.ruta.snapshot.paramMap.get("idK")
    if(id){
      this.idK = +id
    }else{
      this.message = "Greska sa id knjige."
    }

    this.servis.detaljiKnjige(this.idK).subscribe(data=>{
      if(data){
        this.knjiga = data
      }
    })
  }

  ruta = inject(ActivatedRoute)
  servis = inject(ServisService)
  ruter = inject(Router)
  idK = 0
  message = ""
  knjiga: Knjiga = new Knjiga()

  azurirajKnjigu() {
    this.servis.azurirajKnjigu(this.knjiga).subscribe(data=>{
      console.log(data)
      if(data>0){
        this.message = "Podaci knjige azurirani."
      }else{
        this.message = "Podaci knjige nisu azurirani. Greska. "
      }
      this.servis.detaljiKnjige(this.idK).subscribe(data=>{
        if(data){
          this.knjiga = data
        }
      })
    })

  }

  nazad(){
    this.ruter.navigate(["klijent"])
  }
}
