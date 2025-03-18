import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { korisnik } from './models/korisnik';
import { aktivnost } from './models/aktivnost';
import { Prijava } from './models/prijava';

@Injectable({
  providedIn: 'root'
})
export class ServisService {

  private http = inject(HttpClient)

  private backPath = "http://localhost:8080"

  login(k: string, l: string, t: string) {
    const data = {
      korime: k,
      lozinka: l,
      tip: t
    }
    return this.http.post<korisnik>(`${this.backPath}/login`, data)

  }

  aktuelneAktivnosti() {
    return this.http.get<aktivnost[]>(`${this.backPath}/student/aktuelneAktivnosti`);
  }

  dohvatiAktivnostPoId(id: number) {
    return this.http.get<aktivnost>(`${this.backPath}/student/aktivnost`,{params: { id: id.toString() } })
  }

  dohvatiPrijavu(korime: string, id: number) {
    return this.http.get<Prijava>(`${this.backPath}/student/prijava`,{params: {korime: korime, id: id.toString() } })
  }
  svePrijaveStudenta(korime: string){
    return this.http.get<Prijava[]>(`${this.backPath}/student/prijave`,{params: {korime: korime } })
  }
  updateAktivnostiInsertPrijave(sala: string, id: number, korime: string) {
    return this.http.get<number>(`${this.backPath}/student/novaPrijava`,{params: {sala: sala, id: id,korime: korime } })
  }
  aktivnostiNastavnika(korime: string) {
    return this.http.get<aktivnost[]>(`${this.backPath}/nastavnik/aktivnosti`,{params: { korime: korime} })
  }
  promeniStatus(st: number, id: number) {
    return this.http.get<number>(`${this.backPath}/nastavnik/promeniStatus`,{params: {status:st, id:id}})
  }
  dodajAktivnost(naziv: string, datumVreme: string, napravio: string, sala1: boolean, sala2:boolean, sala3: boolean) {
    return this.http.get<number>(`${this.backPath}/nastavnik/dodajAktivnost`,{params: {naziv:naziv, datumVreme:datumVreme,
      napravio:napravio, sala1:sala1, sala2:sala2, sala3:sala3
    }})
  }
  

  constructor() { }

}
