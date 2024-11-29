import { APP_INITIALIZER, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { JeuxComponent } from './jeux/jeux.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { KeycloakAngularModule, KeycloakService } from 'keycloak-angular';
import { MotosComponent } from './motos/motos.component';


function initializeKeycloak(keycloak: KeycloakService) {
  return () =>
  keycloak.init({
  config: {
  url: 'http://localhost:8092',
  realm: 'oussema-realm',
  clientId: 'jeux-app'
  },
  initOptions: {
  /*onLoad :'login-required',
  checkLoginIframe: true*/
   onLoad: 'check-sso',
  silentCheckSsoRedirectUri:
  window.location.origin + '/assets/silent-check-sso.html' 
  }
  });
  }
@NgModule({
  declarations: [
    AppComponent,
    JeuxComponent,
    MotosComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    KeycloakAngularModule
    
  ],
  providers: [{
    provide: APP_INITIALIZER,
    useFactory: initializeKeycloak,
    multi: true,
    deps: [KeycloakService]}

  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
