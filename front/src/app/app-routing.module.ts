import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { JeuxComponent } from './jeux/jeux.component';
import { AuthGuard } from './guards/secure.guard';
import { MotosComponent } from './motos/motos.component';

const routes: Routes = [
  {path: "moto", component : MotosComponent, canActivate:[AuthGuard],
    data : {roles:['ADMIN']}}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
