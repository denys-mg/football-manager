import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PlayersComponent } from "./components/players/players.component";
import { PlayerDetailsComponent } from "./components/player-details/player-details.component";
import { TeamsComponent } from "./components/teams/teams.component";
import { TeamDetailsComponent } from "./components/team-details/team-details.component";
import { PlayerFormComponent } from "./components/player-form/player-form.component";
import { TeamFormComponent } from "./components/team-form/team-form.component";
import { HomeComponent } from "./components/home/home.component";

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'players', component: PlayersComponent},
  {path: 'players/:id', component: PlayerDetailsComponent},
  {path: 'teams', component: TeamsComponent},
  {path: 'teams/:id', component: TeamDetailsComponent},
  {path: 'players-form', component: PlayerFormComponent},
  {path: 'teams-form', component: TeamFormComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
