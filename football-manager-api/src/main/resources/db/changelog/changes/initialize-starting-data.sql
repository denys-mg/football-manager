--liquibase formatted sql
--changeset denMg:initialize-starting-data splitStatements:true endDelimiter:;

INSERT INTO teams VALUES
    (1, "Paris Saint-Germain", "France", "Paris", 2000000, 8.7),
    (2, "AL NASSR", "Saudi Arabian", "Riyadh", 2000000, 6.2),
    (3, "Barcelona", "Spain", "Barcelona", 2000000, 6.3),
    (4, "Bayern Munich", "Germany", "Munich", 2000000, 7.9),
    (5, "Liverpool", "England", "Liverpool", 2000000, 9.1),
    (6, "Real Madrid", "Spain", "Madrid", 2000000, 7.6),
    (7, "Chelsea", "England", "London", 2000000, 6.2),
    (8, "Manchester City", "England", "Manchester", 2000000, 5.5),
    (9, "Tottenham Hotspur", "England", "London", 2000000, 6.4);

INSERT INTO players VALUES
    (1, "Lionel", "Messi", 35, "2004-10-16", 1),
    (2, "Cristiano", "Ronaldo", 38, "2002-10-07", 2),
    (3, "Neymar", "Jr.", 30, "2009-03-07", 1),
    (4, "Robert", "Lewandowski", 33, "2005-08-29", 4),
    (5, "Mohamed", "Salah", 29, "2010-05-01", 5),
    (6, "Antoine", "Griezmann", 31, "2009-08-29", 3),
    (7, "Sergio", "Ramos", 36, "2004-12-06", 6),
    (8, "Eden", "Hazard", 31, "2005-08-10", 7),
    (9, "Kevin", "De Bruyne", 30, "2008-08-28", 8),
    (10, "Harry", "Kane", 28, "2010-08-18", 9);
