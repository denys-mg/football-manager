import { Component, Injectable, OnInit } from '@angular/core';
import { Team } from "../../models/team";
import { TeamService } from "../../services/team.service";

@Component({
  selector: 'app-teams',
  templateUrl: './teams.component.html'
})
@Injectable({
  providedIn: 'root'
})
export class TeamsComponent implements OnInit {
  teams: Team[] = [];
  errorMessage: string = '';
  itemsPerPage: number = 10;
  page: number = 1;

  constructor(private teamService: TeamService) {}

  ngOnInit(): void {
    this.getTeams();
  }

  getTeams(): void {
    this.teamService.getAll()
      .subscribe({
        next: (teams: Team[]) => {
          this.teams = teams;
          this.resetErrorMessage();
          this.checkElementsLength();
        },
        error: (response: any) => {
          console.error(response);
          this.errorMessage = response.error.errMessage;
        }
      });
  }

  delete(team: Team) {
    this.teamService.delete(team.id)
      .subscribe({
        next: () => {
          this.checkLastDeletedOnPage(team);
          this.teams = this.teams.filter(t => t !== team);
          this.resetErrorMessage();
          this.checkElementsLength();
        },
        error: (response: any) => {
          console.error(response);
          this.errorMessage = response.error.errMessage;
        }
      });
  }

  private resetErrorMessage(): void {
    this.errorMessage = '';
  }

  private checkElementsLength(): void {
    if (this.teams.length < 1) {
      this.errorMessage = 'There are no teams';
    }
  }

  private checkLastDeletedOnPage(team: Team) {
    let teamIndex: number = this.teams.indexOf(team);
    if ((teamIndex + 1) % this.itemsPerPage == 1
      && this.teams.length == teamIndex + 1) {
      this.page -= 1;
    }
  }
}
