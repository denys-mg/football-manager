import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { PlayerService } from "../../services/player.service";
import { Player } from "../../models/player";
import { Team } from "../../models/team";
import { TeamService } from "../../services/team.service";

@Component({
  selector: 'app-player-details',
  templateUrl: './player-details.component.html'
})
export class PlayerDetailsComponent implements OnInit {
  player?: Player;
  team?: Team;
  teamToTransferId?: number;
  changeableTeam: boolean = false;
  teams: Team[] = [];
  errorMessage: string = '';
  infoMessage: string = '';

  constructor(
    private route: ActivatedRoute,
    private playerService: PlayerService,
    private teamService: TeamService,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.fetchData();
  }

  fetchData(): void {
    this.getPlayer();
    this.getTeams();
  }

  getPlayer(): void {
    this.playerService.get(this.extractIdFromUrl())
      .subscribe({
        next: (player: Player) => {
          this.player = player;
          this.getTeam();
          this.resetErrorMessage();
        },
        error: (response: any) => {
          console.error(response);
          this.errorMessage = response.error.errMessage;
        }
      });
  }

  getTeam(): void {
    if (this.player?.teamId) {
      this.teamService.get(this.player.teamId)
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
  }

  getTeams(): void {
    this.teamService.getAll()
      .subscribe({
        next: (teams: Team[]) => {
          this.teams = teams;
          this.resetErrorMessage();
          this.setInitialTeamIdToTransfer();
        },
        error: (response: any) => {
          console.error(response);
          this.errorMessage = response.error.errMessage;
          this.infoMessage = '';
        }
      });
  }

  update(): void {
    if (this.player) {
      this.playerService.update(this.player.id, this.player)
        .subscribe({
          next: () => {
            this.getPlayer();
            this.infoMessage = 'Successfully updated';
          },
          error: (response: any) => {
            console.error(response);
            this.errorMessage = response.error.errMessage;
            this.infoMessage = '';
          }
        });
    }
  }

  changeTeam() {
    if (this.player?.id && this.teamToTransferId) {
      this.playerService.changeTeam(this.player.id, this.teamToTransferId)
        .subscribe({
          next: () => {
            this.getPlayer();
            this.resetErrorMessage();
          },
          error: (response: any) => {
            console.error(response);
            this.infoMessage = '';
            this.errorMessage = response.error.errMessage;
          }
        });
    }
  }

  goBack(): void {
    this.location.back();
  }

  private setInitialTeamIdToTransfer(): void {
    if (this.teams.length > 0) {
      this.teamToTransferId = this.teams[0].id;
    } else {
      this.infoMessage = 'You can\'t set a team for this player as no team exists';
    }
  }

  private resetErrorMessage(): void {
    this.errorMessage = '';
  }

  private extractIdFromUrl(): number {
    return Number(this.route.snapshot.paramMap.get('id'));
  }
}
