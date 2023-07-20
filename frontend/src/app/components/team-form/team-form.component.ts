import { Component } from '@angular/core';
import { Router } from "@angular/router";
import { Team } from "../../models/team";
import { TeamService } from "../../services/team.service";
import { TeamsComponent } from "../teams/teams.component";

@Component({
  selector: 'app-team-form',
  templateUrl: './team-form.component.html'
})
export class TeamFormComponent {
  model: Team = new Team(0, '', '', '');
  errorMessage: string = '';

  constructor(private teamService: TeamService,
              private teamsComponent: TeamsComponent,
              private router: Router) {}

  onSubmit() {
      this.create(this.model);
  }

  create(team: Team) {
    this.teamService.create(team)
      .subscribe({
        next: (team: Team) => {
          this.teamsComponent.teams.push(team);
          this.router.navigateByUrl("/teams");
        },
        error: (response: any) => {
          console.error(response);
          this.errorMessage = response.error.errMessage;
        }
      });
  }
}
