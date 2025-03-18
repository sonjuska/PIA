import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ServisService } from '../servis.service';
import { aktivnost } from '../models/aktivnost';
import { korisnik } from '../models/korisnik';

@Component({
  selector: 'app-aktivnost-details',
  standalone: true,
  imports: [],
  templateUrl: './aktivnost-details.component.html',
  styleUrl: './aktivnost-details.component.css'
})
export class AktivnostDetailsComponent implements OnInit{
  disabled1: boolean = false;
  disabled2: boolean = false;
  disabled3: boolean = false;
  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get("id")
    const k = localStorage.getItem("loggedUser")
    if(k){
      this.korisnik = JSON.parse(k)
    }
    if(id){

      this.id = Number(id)
      this.servis.dohvatiAktivnostPoId(Number(id)).subscribe(data=>{
        this.aktivnost = data

        if(this.aktivnost.sala1==0){
          this.disabled1 = true;
        }
        if(this.aktivnost.sala2==0){
          this.disabled2 = true;
        }
        if(this.aktivnost.sala3==0){
          this.disabled3 = true;
        }
      })

      
    }else{
      this.poruka = "Greska."
    }
    
  }

  id = 0;
  korisnik: korisnik = new korisnik()
  poruka = ""
  aktivnost: aktivnost = new aktivnost()
  private route = inject(ActivatedRoute)
  private servis = inject(ServisService)
  private router = inject(Router)

  prijaviSeNaAktivnost(sala: string) {
    this.servis.updateAktivnostiInsertPrijave(sala,this.id,this.korisnik.korime).subscribe(data=>{
      if(data<=0){
        this.poruka = "Greska."
      }

      this.servis.dohvatiAktivnostPoId(Number(this.id)).subscribe(data=>{
        this.aktivnost = data

        if(this.aktivnost.sala1==0){
          this.disabled1 = true;
        }
        if(this.aktivnost.sala2==0){
          this.disabled2 = true;
        }
        if(this.aktivnost.sala3==0){
          this.disabled3 = true;
        }
      })
      this.router.navigate(["/student"])
    })
  }



}
