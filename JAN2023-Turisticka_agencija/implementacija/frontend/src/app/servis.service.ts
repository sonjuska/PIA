import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Putnik } from './models/putnik';
import { Agencija } from './models/agencija';
import { Observable } from 'rxjs';
import { Usluga } from './models/usluga';
import { KupljeneUslugeAgencija } from './models/kupljeneUslugeAgencija';

@Injectable({
  providedIn: 'root'
})
export class ServisService {

  constructor() { }
  private http = inject(HttpClient)

  private backPath = "http://localhost:8080"

  login(korime: string, lozinka:string, tip: string): Observable<Putnik|Agencija>{

    if(tip=="putnik"){
      const data = {
        korisnickoime: korime,
        lozinka: lozinka,
        ime_prezime: "",
        imejl: "",
        lokacija_trenutna: "",
        novac: ""
      }
      return this.http.post<Putnik>(`${this.backPath}/loginPutnik`, data)
    }else{
      const data = {
        korisnickoime: korime,
        lozinka: lozinka,
        naziv: "",
        PIB: 0
      }
      return this.http.post<Agencija>(`${this.backPath}/loginAgencija`, data)
    }
  }

  uslugePoId(korisnickoime: string) {
    return this.http.get<Usluga[]>(`${this.backPath}/uslugePoIdA`, {params: {korisnickoime: korisnickoime}})
  }

  dodajUslugu(idAgencije: string, tip:string, lokacija_od:string, lokacija_do:string, period:string, cena:number, broj_mesta:number){
    return this.http.get<number>(`${this.backPath}/dodajUslugu`,{params: {
      idAgencije: idAgencije,
      tip: tip,
      lokacija_od: lokacija_od,
      lokacija_do: lokacija_do,
      period: period,
      cena: cena,
      broj_mesta: broj_mesta
    }})
  }

  sveLokacije(id_putnik: string){
    return this.http.get<string[]>(`${this.backPath}/sveLokacije`, {params: {id_putnik: id_putnik}})
  }
  trenutnaLokacija(id_putnik: string){
    return this.http.get<string>(`${this.backPath}/trenutnaLokacija`, {params: {id_putnik: id_putnik}, responseType: 'text' as 'json'})
  }
  promeniTrenutnu(id_putnik: string, lokacija: string){
    return this.http.get<number>(`${this.backPath}/promeniTrenutnuLokaciju`, {params: {id_putnik: id_putnik, lokacija: lokacija}})
  }

  dohvatiKupljeneUsluge(korisnickoime: string) {
    return this.http.get<KupljeneUslugeAgencija[]>(`${this.backPath}/dohvatiKupljeneUsluge`, {params: {korisnickoime:korisnickoime}})
  }

  pretrazi(){
    return this.http.get<Usluga[]>(`${this.backPath}/dohvatiUsluge`)
  }

  dodajKupljenuUslugu(id: number, brSap: number, korisnickoime: string) {
    return this.http.get<number>(`${this.backPath}/dodajKupljenuUslugu`, {params: {id_usluge:id, id_putnik:korisnickoime, broj_saputnika: brSap }})
  }

  getPutnik(korisnickoime: string, lozinka: string) {
    return this.http.get<Putnik>(`${this.backPath}/getPutnik`, {params: {korisnickoime:korisnickoime, lozinka:lozinka}})
  }
  getAgencija(korisnickoime:string, lozinka:string){
    return this.http.get<Agencija>(`${this.backPath}/getAgencija`, {params: {korisnickoime:korisnickoime,lozinka:lozinka}})
  }
}
