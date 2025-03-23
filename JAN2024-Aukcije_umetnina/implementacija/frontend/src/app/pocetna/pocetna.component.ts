import { Component, inject } from '@angular/core';
import { ServisService } from '../servis.service';
import { Korisnik } from '../models/korisnik';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-pocetna',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './pocetna.component.html',
  styleUrl: './pocetna.component.css'
})
export class PocetnaComponent {

  korisnik: Korisnik = new Korisnik()

  sviKorisnici: Korisnik[] = []

  private userService = inject(ServisService)

  ngOnInit(): void {

  }

  username = ""
  password = ""
  type = ""
  message = ""

  private ruter = inject(Router)

  login(){
    this.userService.login(this.username, this.password, this.type).subscribe(data=>{
      if(data==null){
        this.message = "Pogrešni podaci."
      }
      else{
        this.message = ""
        localStorage.setItem("loggedUser", JSON.stringify(data))
        if(data.type=="kupac"){
          this.ruter.navigate(["/kupac"])
        }
        else{
          this.ruter.navigate(["/prodavac"])
        }
      }
    })
  }
}
