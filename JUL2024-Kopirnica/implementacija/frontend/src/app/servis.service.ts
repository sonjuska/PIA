import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Korisnik } from './models/korisnik';
import { Knjiga } from './models/knjiga';
import { Zahtev } from './models/zahtev';
import { Opcija } from './models/opcija';
import { ZahtevJoinKlijent } from './models/zahtevJoinKlijent';

@Injectable({
  providedIn: 'root'
})
export class ServisService {

  private http = inject(HttpClient)

  private backPath = "http://localhost:8080"

  login(u: string, p: string){
    const data = {
      kor_ime: u,
      lozinka: p
    }
    return this.http.post<Korisnik>(`${this.backPath}/login`, data)
  }

  knjigeKlijent(kor_ime: string){
    return this.http.get<Knjiga[]>(`${this.backPath}/knjigeKlijent`, {params: {kor_ime:kor_ime}})
  }
  detaljiKnjige(idK: number) {
    return this.http.get<Knjiga>(`${this.backPath}/knjigaDetalji`, {params: {idK:idK}})
  }
  azurirajKnjigu(knjiga: Knjiga) {
    return this.http.get<number>(`${this.backPath}/azurirajKnjigu`, {params: {naziv:knjiga.naziv, strane:knjiga.strane, idK:knjiga.idK}})
  }
  zahtevi(kor_ime: string) {
    return this.http.get<Zahtev[]>(`${this.backPath}/zahteviKlijent`, {params:{kor_ime:kor_ime}})
  }
  getZahtev(idZ: number) {
    return this.http.get<Zahtev>(`${this.backPath}/zahtev`, {params:{idZ:idZ}})
  }
  getOpcije() {
    return this.http.get<Opcija[]>(`${this.backPath}/opcije`)
  }
  azurirajZahtev(selektovaneOpcije: { [key: string]: string; }, idZ:number, racun: number, idK: number) {
    let opcije = Object.values(selektovaneOpcije).join(","); 
    return this.http.get<number>(`${this.backPath}/azurirajZahtev`, {params:{opcije: opcije, idZ:idZ, racun:racun, idK:idK}})
  }
  getNovZahtev() {
    return this.http.get<ZahtevJoinKlijent[]>(`${this.backPath}/noviZahtevi`)
  }
  obrisiOpciju(o: Opcija) {
    return this.http.get<number>(`${this.backPath}/obrisiOpciju`, {params:{naziv: o.naziv, cena:o.cena, kategorijausluge:o.kategorijausluge }})
  }
  prihvatiZahtev(z: ZahtevJoinKlijent) {
    return this.http.get<number>(`${this.backPath}/promeniStatusZahteva`, {params:{idZ:z.idZ}})
  }
  constructor() { }
}
