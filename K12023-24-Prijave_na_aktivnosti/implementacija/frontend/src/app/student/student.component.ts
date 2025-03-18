import { Component, inject } from '@angular/core';
import { korisnik } from '../models/korisnik';
import { Router } from '@angular/router';
import { aktivnost } from '../models/aktivnost';
import { ServisService } from '../servis.service';
import { Prijava } from '../models/prijava';

@Component({
  selector: 'app-student',
  standalone: true,
  imports: [],
  templateUrl: './student.component.html',
  styleUrl: './student.component.css'
})
export class StudentComponent {
  
ruter = inject(Router)
servis = inject(ServisService)

korisnik: korisnik = new korisnik()
aktivnosti: aktivnost[] = []
prijave: Prijava[] = []

poruka = ""

  ngOnInit(): void {
    
    this.poruka = ""
    let k = localStorage.getItem("loggedUser");

    if(k){
      this.korisnik = JSON.parse(k);
    }

    this.servis.aktuelneAktivnosti().subscribe(data=>{
      if(data==null){
        this.poruka = "Greska."
      }else{
        this.aktivnosti = data;
        if(this.aktivnosti.length==0){
          this.poruka = "Nema aktuelnih aktinvosti."
        }
      }

    })

    this.servis.svePrijaveStudenta(this.korisnik.korime).subscribe(data=>{
      if(data!=null){
        this.prijave=data
      }
    })
    
  }

  odjava() {
    localStorage.removeItem("loggedUser")
    this.ruter.navigate([""])
  }

  prijavljen(id: number): boolean {
    
    for(let p of this.prijave){
      if(parseInt(p.aktivnost)==id){
        return true;
      }
    }
    return false;
    }

}
