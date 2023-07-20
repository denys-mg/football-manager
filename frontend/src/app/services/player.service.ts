import { Injectable } from '@angular/core';
import { Player } from "../models/player";
import { HttpClient } from "@angular/common/http";
import { AbstractService } from "./abstract.service";
import { Observable } from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class PlayerService extends AbstractService<Player> {
  protected override baseUrl = 'http://localhost:8080/players';

  constructor(protected override http: HttpClient) {
    super(http);
  }

  getPlayers(teamId: number): Observable<Player[]> {
    const url = `${this.baseUrl}/team/${teamId}`;
    return this.http.get<Player[]>(url);
  }

  changeTeam(playerId: number, teamId: number): Observable<Player> {
    const url = `${this.baseUrl}/transfer?playerId=${playerId}&teamId=${teamId}`;
    return this.http.put<Player>(url, null, this.httpOptions);
  }
}
