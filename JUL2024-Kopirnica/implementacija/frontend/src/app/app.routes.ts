import { Routes } from '@angular/router';
import { PocetnaComponent } from './pocetna/pocetna.component';
import { RadnikComponent } from './radnik/radnik.component';
import { KlijentComponent } from './klijent/klijent.component';
import { KnjigaDetailsComponent } from './knjiga-details/knjiga-details.component';
import { UrediComponent } from './uredi/uredi.component';


export const routes: Routes = [
    {path: "", component: PocetnaComponent},
    {path: "radnik", component: RadnikComponent},
    {path: "klijent", component: KlijentComponent},
    {path: "knjige/:idK", component: KnjigaDetailsComponent},
    {path: "uredi/:idZ", component: UrediComponent}
];
