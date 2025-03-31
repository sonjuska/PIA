import { Component, inject } from '@angular/core';
import {FormsModule} from '@angular/forms'
import { ServisService } from '../servis.service';
import { Router } from '@angular/router';
import { Korisnik } from '../models/korisnik';

@Component({
  selector: 'app-pocetna',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './pocetna.component.html',
  styleUrl: './pocetna.component.css'
})
export class PocetnaComponent {


  servis = inject(ServisService)
  ruter = inject(Router)

  korime=""
  lozinka=""
  message=""
  korisnik: Korisnik = new Korisnik()

  login(){
    this.servis.login(this.korime,this.lozinka).subscribe(data=>{
      if(data){
        localStorage.setItem("logged",JSON.stringify(data))
        if(data.tip=="klijent")
        this.ruter.navigate(["klijent"])
        else
        this.ruter.navigate(["radnik"])
      }else{
        this.message="Pogresno uneti podaci. Pokusajte ponovo."
      }
    })
  }
}
