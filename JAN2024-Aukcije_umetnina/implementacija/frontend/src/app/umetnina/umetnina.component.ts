import { Component, inject } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Umetnina } from '../models/umetnina';
import { ServisService } from '../servis.service';
import { Korisnik } from '../models/korisnik';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-umetnina',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './umetnina.component.html',
  styleUrl: './umetnina.component.css'
})
export class UmetninaComponent {


  route = inject(ActivatedRoute);
  servis = inject(ServisService)
  ruter = inject(Router)


  ngOnInit(): void {
    let idA = this.route.snapshot.paramMap.get("idA");

    if (idA) {
      this.idA = idA;
      
      this.servis.dohvatiUmetninePoIdA(idA).subscribe((data) => {
        if (data) {
          this.umetnine = data;
        }
      });
    } else {
      this.message = "Greska";
    }
  }

  idA = ""
  umetnine: Umetnina[] = []
  message = ""
  korisnik: Korisnik = new Korisnik()

  Potvrdi(ponuda: number, idU: number,idA: number, naziv: string) {
    this.message = ""
    let korisnik = localStorage.getItem("loggedUser")
    if(korisnik){
      this.korisnik = JSON.parse(korisnik)
    }
    let vlasnik = this.korisnik.username
    
    this.servis.updatePonuda(idU, idA, naziv, ponuda, vlasnik).subscribe((value)=>{
      console.log(value)
      if(+value<0){
        this.message = "Ponuda mora biti veca od trenutne!"
      }

      this.servis.dohvatiUmetninePoIdA(this.idA).subscribe((data) => {
        if (data) {
          this.umetnine = data;
          //console.log(this.umetnine);
        }
      });
    });


  }

  nazad(){
    this.ruter.navigate(["/kupac"])
  }

}
