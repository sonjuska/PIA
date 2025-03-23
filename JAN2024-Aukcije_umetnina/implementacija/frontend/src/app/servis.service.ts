import { inject, Injectable } from '@angular/core';
import { Korisnik } from './models/korisnik';
import { HttpClient } from '@angular/common/http';
import { Aukcija } from './models/aukcija';
import { Umetnina } from './models/umetnina';

@Injectable({
  providedIn: 'root'
})
export class ServisService {

  constructor() { }

  private http = inject(HttpClient)

  private backPath = "http://localhost:8080"

  login(u: string, p: string, t: string){
    const data = {
      username: u,
      password: p,
      type: t
    }
    return this.http.post<Korisnik>(`${this.backPath}/login`, data)
  }

  dohvatiAktivneAukcije() {
    return this.http.get<Aukcija[]>(`${this.backPath}/aktivneAukcije`)
  }

  dohvatiSveAukcije(){
    return this.http.get<Aukcija[]>(`${this.backPath}/sveAukcije`)
  }

  dohvatiNeaktivneAukcije(){
    return this.http.get<Aukcija[]>(`${this.backPath}/neaktivneAukcije`)
  }

  dohvatiUmetninePoIdA(idA: string) {
    return this.http.get<Umetnina[]>(`${this.backPath}/aukcija/${idA}`);
  }

  updatePonuda(U: number, A: number, naz:string, pon: number, vlas: string) {
    const data={
      idU: U,
      idA: A,
      naziv: naz,
      ponuda: pon,
      vlasnik: vlas
    }
    return this.http.post<Number>(`${this.backPath}/ponudi`, data)
  }

  maksUmetninaId(){
    return this.http.get<Number>(`${this.backPath}/maksUmetninaId`)
  }

  dohvatiKupljeneUmetnine(username: string) {
    return this.http.get<Umetnina[]>(`${this.backPath}/kupljeneUmetnine`, {params:{username:username}})
  }
  
  dohvatiSveKupljeneUmetnine() {
    return this.http.get<Umetnina[]>(`${this.backPath}/sveKupljeneUmetnine`)
  }
}
