import { Routes } from '@angular/router';
import { PocetnaComponent } from './pocetna/pocetna.component';
import { KupacComponent } from './kupac/kupac.component';
import { ProdavacComponent } from './prodavac/prodavac.component';
import { UmetninaComponent } from './umetnina/umetnina.component';

export const routes: Routes = [
    {path: "", component: PocetnaComponent},
    {path: "kupac", component: KupacComponent},
    {path: "prodavac", component: ProdavacComponent},
    {path: "aukcija/:idA", component: UmetninaComponent}
];
