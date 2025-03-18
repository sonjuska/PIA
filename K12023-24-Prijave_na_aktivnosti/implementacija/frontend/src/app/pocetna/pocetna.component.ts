import { Component, inject, OnInit } from '@angular/core';
import {FormsModule} from '@angular/forms'
import { ServisService } from '../servis.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-pocetna',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './pocetna.component.html',
  styleUrl: './pocetna.component.css'
})
export class PocetnaComponent implements OnInit{

  ngOnInit(): void {
    
  }

  korime = ""
  lozinka = ""
  poruka=""
  tip = ""

  servis = inject(ServisService)
  ruter = inject(Router)

  login(){
    if(this.korime==""){
      this.poruka = "Niste uneli korisnicko ime."
    }
    else if(this.lozinka==""){
      this.poruka = "Niste uneli lozinku."
    }else if(this.tip==""){
      this.poruka = "Niste izabrali tip."
    }else{

      this.servis.login(this.korime, this.lozinka, this.tip).subscribe(data=>{

        if(data==null){
          this.poruka = "Pogresno korisnicko ime, lozinka ili tip."
        }
        else{
          this.poruka= ""
          localStorage.setItem("loggedUser", JSON.stringify(data))
          if(data.tip=="student"){
            
            this.ruter.navigate(["/student"])
          }
          else{
            
            this.ruter.navigate(["/nastavnik"])
          }
        }
      })
    }
    
  }
}
