import { Component, inject, OnInit } from '@angular/core';
import { Korisnik } from '../models/korisnik';
import { ServisService } from '../servis.service';
import { Knjiga } from '../models/knjiga';
import { Router } from '@angular/router';
import { Zahtev } from '../models/zahtev';
import { Opcija } from '../models/opcija';

@Component({
  selector: 'app-klijent',
  standalone: true,
  imports: [],
  templateUrl: './klijent.component.html',
  styleUrl: './klijent.component.css'
})
export class KlijentComponent implements OnInit{

  
  ngOnInit(): void {
    let kor = localStorage.getItem("logged")
    if(kor){
      this.k= JSON.parse(kor)
    }

    this.servis.knjigeKlijent(this.k.kor_ime).subscribe(data=>{
      if(data){
        this.knjige = data
      }else{
        this.message = "Klijent nema knjige."
      }
    })

    this.servis.zahtevi(this.k.kor_ime).subscribe(data=>{
      if(data){
        this.zahtevi = data
      }else{
        console.log("greska u zahtevima")
      }
    })
  }

  ruter = inject(Router)
  servis = inject(ServisService)
  k: Korisnik = new Korisnik()
  knjige: Knjiga[] = []
  message = ""
  zahtevi: Zahtev[] = []


  izmeni(knjiga: Knjiga){
    this.ruter.navigate([`knjige/${knjiga.idK}`])
  }

  logout(){
    localStorage.removeItem("logged")
    this.ruter.navigate([""])
  }

  boja(status: string) {
    if(status=="nov"){
      return "nov"
    }
    else if(status=="prihvacen"){
      return "prihvacen"
    }
  
    return ""
  }

  uredi(z: Zahtev) {
    this.ruter.navigate([`uredi/${z.idZ}`], {
      queryParams: { knjiga: z.knjiga}
    });
  }
}
