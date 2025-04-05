import { Component, inject, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Putnik } from '../models/putnik';
import { ServisService } from '../servis.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-br-saputnika',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './br-saputnika.component.html',
  styleUrl: './br-saputnika.component.css'
})
export class BrSaputnikaComponent implements OnInit{
ngOnInit(): void {
  let k = localStorage.getItem("loggedUser")
  if(k){
    this.korisnik = JSON.parse(k)
  }

  let id = this.route.snapshot.paramMap.get("id")
  if(id) this.id = +id

}
kupi() {
  this.servis.dodajKupljenuUslugu(this.id, this.brSap,this.korisnik.korisnickoime).subscribe(data=>{
    if(data>0){
      this.ruter.navigate(["putnik"])
    }else if(data==-2){
      this.message = "Nemate dovoljno novca."
    }else{
      console.log(data)
      this.message = "Greska"
    }

  })

}

nazad(){
  this.ruter.navigate(["putnik"])
}
servis = inject(ServisService)
route = inject(ActivatedRoute)
ruter = inject(Router)
brSap= 0;
id=0
korisnik: Putnik = new Putnik()
message = ""

}
