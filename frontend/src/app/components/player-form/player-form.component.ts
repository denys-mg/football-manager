import { Component, OnInit } from '@angular/core';
import { Player } from "../../models/player";
import { PlayerService } from "../../services/player.service";
import { PlayersComponent } from "../players/players.component";
import { Router } from '@angular/router';
import { TeamService } from "../../services/team.service";
import { Team } from "../../models/team";

@Component({
  selector: 'app-player-form',
  templateUrl: './player-form.component.html'
})
export class PlayerFormComponent implements OnInit {
  teams: Team[] = [];
  model: Player = new Player(0, '', '', '');
  errorMessage: string = '';

  constructor(private playerService: PlayerService,
              private teamService: TeamService,
              private playersComponent: PlayersComponent,
              private router: Router) {}

  ngOnInit() {
    this.getTeams();
  }

  getTeams(): void {
    this.teamService.getAll()
      .subscribe({
        next: (teams: Team[]) => {
          this.teams = teams;
          this.resetErrorMessage();
        },
        error: (response: any) => {
          console.error(response);
          this.errorMessage = response.error.errMessage;
        }
      });
  }

  create(player: Player) {
    this.playerService.create(player)
      .subscribe({
        next: (player: Player) => {
          this.playersComponent.players.push(player);
          this.router.navigateByUrl("/players");
        },
        error: (response: any) => {
          console.error(response);
          this.errorMessage = response.error.errMessage;
        }
      });
  }

  onSubmit() {
    this.create(this.model);
  }

  private resetErrorMessage(): void {
    this.errorMessage = '';
  }
}
