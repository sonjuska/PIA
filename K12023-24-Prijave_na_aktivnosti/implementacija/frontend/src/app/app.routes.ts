import { Routes } from '@angular/router';
import { PocetnaComponent } from './pocetna/pocetna.component';
import { StudentComponent } from './student/student.component';
import { NastavnikComponent } from './nastavnik/nastavnik.component';
import { AktivnostDetailsComponent } from './aktivnost-details/aktivnost-details.component';

export const routes: Routes = [
    {path: "", component: PocetnaComponent},
    {path: "student", component: StudentComponent},
    {path: "nastavnik", component: NastavnikComponent},
    {path: 'student/:id', component: AktivnostDetailsComponent }
];
