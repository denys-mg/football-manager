export class Player {
  constructor(
    public id: number,
    public firstname: string,
    public lastname: string,
    public careerStartDate: string,
    public age?: number,
    public teamId?: number
  ) {}
}
