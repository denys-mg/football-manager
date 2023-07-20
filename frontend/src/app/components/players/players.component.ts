import { Component, Injectable, OnInit } from '@angular/core';
import { Player } from '../../models/player';
import { PlayerService } from '../../services/player.service';

@Component({
  selector: 'app-players',
  templateUrl: './players.component.html'
})
@Injectable({
  providedIn: 'root'
})
export class PlayersComponent implements OnInit {
  players: Player[] = [];
  errorMessage: string = '';
  itemsPerPage: number = 10;
  page: number = 1;

  constructor(private playerService: PlayerService) {}

  ngOnInit(): void {
    this.getPlayers();
  }

  getPlayers(): void {
    this.playerService.getAll()
      .subscribe({
        next: (players: Player[]) => {
          this.players = players;
          this.resetErrorMessage();
          this.checkElementsLength();
        },
        error: (response: any) => {
          console.error(response);
        }
      });
  }

  delete(player: Player) {
    this.playerService.delete(player.id)
      .subscribe({
        next: () => {
          this.checkLastDeletedOnPage(player);
          this.players = this.players.filter(p => p !== player);
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
    if (this.players.length < 1) {
      this.errorMessage = 'There are no players';
    }
  }

  private checkLastDeletedOnPage(player: Player) {
    let playerIndex: number = this.players.indexOf(player);
    if ((playerIndex + 1) % this.itemsPerPage == 1
        && this.players.length == playerIndex + 1) {
      this.page -= 1;
    }
  }
}
