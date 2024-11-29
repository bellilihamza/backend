import { Component } from '@angular/core';
import { Jeux } from '../model/jeux.model';
import { JeuxService } from '../jeux.service';
import { Moto } from '../model/moto.model';
@Component({
  selector: 'app-jeux',
  templateUrl: './motos.component.html',
  styleUrls: ['./motos.component.css']
})
export class MotosComponent {
  jeu?: Jeux[];
  motos! : Moto[];



  constructor(private jeuxService: JeuxService ) {
    // this.jeux = jeuxService.listeJeux();
  }



  ngOnInit(): void {
    this.chargerMotos();

  }

 
  chargerMotos(){
    this.jeuxService.listeMoto().subscribe(mot => {
      console.log(mot);
      this.motos = mot;
      });
  }
   

}
