import { Injectable } from '@angular/core';
import { Team } from "../models/team";
import { HttpClient } from "@angular/common/http";
import { AbstractService } from "./abstract.service";

@Injectable({
  providedIn: 'root'
})
export class TeamService extends AbstractService<Team> {
  protected override baseUrl = 'http://localhost:8080/teams';

  constructor(protected override http: HttpClient) {
    super(http);
  }
}
