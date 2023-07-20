import Decimal from 'decimal.js';

export class Team {
  constructor(
    public id: number,
    public name: string,
    public country: string,
    public city: string,
    public balance?: Decimal,
    public fee?: number
  ) {}
}
