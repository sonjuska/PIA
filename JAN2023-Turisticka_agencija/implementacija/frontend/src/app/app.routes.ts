import { Routes } from '@angular/router';
import { PocetnaComponent } from './pocetna/pocetna.component';
import { AgencijaComponent } from './agencija/agencija.component';
import { PutnikComponent } from './putnik/putnik.component';
import { BrSaputnikaComponent } from './br-saputnika/br-saputnika.component';

export const routes: Routes = [
    {path: "", component: PocetnaComponent},
    {path: "agencija", component: AgencijaComponent},
    {path: "putnik", component: PutnikComponent},
    {path: "putnik/:id", component: BrSaputnikaComponent}
];
