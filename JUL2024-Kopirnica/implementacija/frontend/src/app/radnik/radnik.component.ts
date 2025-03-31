import { Component, inject, OnInit } from '@angular/core';
import { Zahtev } from '../models/zahtev';
import { ServisService } from '../servis.service';
import { ZahtevJoinKlijent } from '../models/zahtevJoinKlijent';
import { Opcija } from '../models/opcija';
import { Router } from '@angular/router';

@Component({
  selector: 'app-radnik',
  standalone: true,
  imports: [],
  templateUrl: './radnik.component.html',
  styleUrl: './radnik.component.css'
})
export class RadnikComponent  implements OnInit{

  ngOnInit(): void {
    this.servis.getNovZahtev().subscribe(data=>{
      if(data){
        this.zahtevi=data
      }else{
        console.log("Greska.")
      }
    })
    this.servis.getOpcije().subscribe(data=>{
      if(data){
        this.opcije=data
      }else{
        console.log("Greska.")
      }
    })
  }

  servis=inject(ServisService)
  ruter=inject(Router)

  zahtevi: ZahtevJoinKlijent[] = []
  opcije: Opcija[] = [];
  message=""

  prihvati(z:ZahtevJoinKlijent) {
    this.servis.prihvatiZahtev(z).subscribe(data=>{
      if(data>0){
        this.message="Zahtev prihvacen."
      }else{
        this.message="Greska. Zahtev nije prihvacen."
      }

      this.servis.getNovZahtev().subscribe(data=>{
        if(data){
          this.zahtevi=data
        }
      })
    })
  }

  obrisi(o: Opcija) {
    this.servis.obrisiOpciju(o).subscribe(data=>{
      if(data>0){
        this.message="Opcija uspesno obrisana."
      }else{
        this.message="Greska. Opcija nije obrisana."
      }
      this.servis.getOpcije().subscribe(data=>{
        if(data){
          this.opcije=data
        }
      })
    })
  }
  logout(){
    localStorage.removeItem("logged")
    this.ruter.navigate([""])
  }

}
