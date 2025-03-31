import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ServisService } from '../servis.service';
import { Zahtev } from '../models/zahtev';
import { Opcija } from '../models/opcija';


@Component({
  selector: 'app-uredi',
  standalone: true,
  imports: [],
  templateUrl: './uredi.component.html',
  styleUrl: './uredi.component.css'
})
export class UrediComponent implements OnInit{

  ngOnInit(): void {
    this.ruta.paramMap.subscribe(params => {
      const idZ = params.get("idZ");
      if (idZ) {
        //console.log(+idZ);
        this.servis.getZahtev(+idZ).subscribe(data => {
          if (data) {
            this.zahtev = data;
            //console.log(this.zahtev)

            this.zahtev.opcije.forEach(op => {
              const option = this.opcije.find(o => o.naziv === op);
              if (option) {
                this.selektovaneOpcije[option.kategorijausluge] = option.naziv;
              }
            })
            console.log(this.selektovaneOpcije)
          } else {
            console.log("Greska sa zahtevom");
          }
        });
      }
    });
    this.servis.getOpcije().subscribe(data=>{
      if(data){
        this.opcije = data
      }
    })

    this.ruta.queryParams.subscribe(data => {
      if(data["knjiga"]){
        this.idK=+data["knjiga"]
        console.log(this.idK)
      }else{
        console.log("Greska pri slanju idK.")
      }
    });
  }


  ruta = inject(ActivatedRoute)
  servis = inject(ServisService)
  ruter = inject(Router)
  message=""
  idK=0

  zahtev: Zahtev = new Zahtev()
  opcije: Opcija[] = []
  selektovaneOpcije: { [key: string]: string } = {}; 

  /*
  selektuj(naziv: string) {
    for(let o of this.zahtev.opcije){
      if(o==naziv){
        return true
      }
    }
    return false
  }
*/
  toggleOpcija(o: Opcija) {
    if (this.selektovaneOpcije[o.kategorijausluge] !== o.naziv) {
      this.selektovaneOpcije[o.kategorijausluge] = o.naziv;
      console.log("Promenjena/dodata kategorija")
      
    } else {
      delete this.selektovaneOpcije[o.kategorijausluge];
      console.log("Destiklirano")
    }
    console.log(this.selektovaneOpcije)
  }

  nazad(){
    this.zahtev= new Zahtev()
    this.ruter.navigate(["klijent"])
  }

  azurirajZahtev() {
    if(Object.keys(this.selektovaneOpcije).length === 0){
      this.message="Bar jedna opcija mora biti selektovana."
      return
    }else{
      let racun = 0;
      for (let k in this.selektovaneOpcije) {
        let op = this.opcije.find(o => o.naziv === this.selektovaneOpcije[k]);
        if (op) {
          racun += op.cena;
        }
      }
      
      this.servis.azurirajZahtev(this.selektovaneOpcije, this.zahtev.idZ, racun, this.idK).subscribe(data=>{
        if(data>0){
          this.message="Zahtev uspešno ažuriran."
        }else
        this.message="Greška. Zahtev nije ažuriran."
      })
    }

  }
  
}
