import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from "@angular/forms";
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from "@angular/common/http";
import { NgxPaginationModule } from 'ngx-pagination';

import { AppComponent } from './app.component';
import { HomeComponent } from "./components/home/home.component";
import { PlayersComponent } from './components/players/players.component';
import { PlayerDetailsComponent } from './components/player-details/player-details.component';
import { TeamsComponent } from './components/teams/teams.component';
import { TeamDetailsComponent } from './components/team-details/team-details.component';
import { PlayerFormComponent } from './components/player-form/player-form.component';
import { TeamFormComponent } from './components/team-form/team-form.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    PlayersComponent,
    PlayerDetailsComponent,
    TeamsComponent,
    TeamDetailsComponent,
    PlayerFormComponent,
    TeamFormComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NgxPaginationModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
