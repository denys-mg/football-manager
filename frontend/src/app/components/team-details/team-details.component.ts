import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from "@angular/router";
import { Location } from "@angular/common";
import { Team } from "../../models/team";
import { TeamService } from "../../services/team.service";
import { Player } from "../../models/player";
import { PlayerService } from "../../services/player.service";

@Component({
  selector: 'app-team-details',
  templateUrl: './team-details.component.html'
})
export class TeamDetailsComponent implements OnInit {
  team?: Team;
  players?: Player[];
  errorMessage: string = '';
  infoMessage: string = '';
  hasPlayers: boolean = true;
  itemsPerPage: number = 5;
  page: number = 1;

  constructor(
    private route: ActivatedRoute,
    private teamService: TeamService,
    private playerService: PlayerService,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.getTeam();
    this.getPlayers();
  }

  getTeam(): void {
    this.teamService.get(this.extractIdFromUrl())
      .subscribe({
        next: (team: Team) => {
          this.team = team;
          this.resetErrorMessage();
        },
        error: (response: any) => {
          console.error(response);
          this.errorMessage = response.error.errMessage;
        }
      });
  }

  getPlayers(): void {
    this.playerService.getPlayers(this.extractIdFromUrl())
      .subscribe({
        next: (players: Player[]) => {
          this.players = players;
          this.resetErrorMessage();
          if (this.players.length < 1) {
            this.hasPlayers = false;
          }
        },
        error: (response: any) => {
          console.error(response);
          this.errorMessage = response.error.errMessage;
        }
      });
  }

  update(): void {
    if (this.team) {
      this.teamService.update(this.team.id, this.team)
        .subscribe({
          next: () => {
            this.infoMessage = 'Successfully updated';
            this.resetErrorMessage();
          },
          error: (response: any) => {
            console.error(response);
            this.errorMessage = response.error.errMessage;
            this.infoMessage = '';
          }
        });
    }
  }

  goBack(): void {
    this.location.back();
  }

  private extractIdFromUrl(): number {
    return Number(this.route.snapshot.paramMap.get('id'));
  }

  private resetErrorMessage(): void {
    this.errorMessage = '';
  }
}
