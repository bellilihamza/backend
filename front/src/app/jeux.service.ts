import { Injectable } from '@angular/core';
import { Jeux } from './model/jeux.model';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Moto } from '../app/model/moto.model';


@Injectable({
  providedIn: 'root'
})
export class JeuxService {


  apiURL2: string = 'http://localhost:8081/motos/api';

  Jeux! : Jeux[]; //un tableau de produits
  //categories : Categorie[];
  motos! : Moto[]; 


  constructor(private http : HttpClient) { 
    
  }
  listeMoto(): Observable<Moto[]>{
    return this.http.get<Moto[]>(this.apiURL2);
    }
   
}
