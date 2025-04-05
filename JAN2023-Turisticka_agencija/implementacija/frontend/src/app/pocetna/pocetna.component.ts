import { Component, inject } from '@angular/core';
import {FormsModule} from '@angular/forms'
import { Putnik } from '../models/putnik';
import { Agencija } from '../models/agencija';
import { ServisService } from '../servis.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-pocetna',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './pocetna.component.html',
  styleUrl: './pocetna.component.css'
})
export class PocetnaComponent {

  private servis = inject(ServisService)
  private ruter = inject(Router)
  korime=""
  lozinka=""
  agencija=""
  putnik=""
  message = ""

  login(){
    let tip=this.agencija
    if(!tip){
      tip=this.putnik
    }
    console.log(tip)
    this.servis.login(this.korime, this.lozinka, tip).subscribe(data=>{
      if(data){
        localStorage.setItem("loggedUser", JSON.stringify(data));
        if(tip=="agencija"){
          this.ruter.navigate(["/agencija"])
        }else if(tip=="putnik"){
          this.ruter.navigate(["/putnik"])
        }
      }else{
        this.message="Pogresno uneti podaci."
      }
    })
  this.agencija=""
  this.putnik=""
  }
}
