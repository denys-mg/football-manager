--liquibase formatted sql
--changeset denMg:create-players-table splitStatements:true endDelimiter:;

CREATE TABLE IF NOT EXISTS players (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    firstname VARCHAR(255) NOT NULL,
    lastname VARCHAR(255) NOT NULL,
    age INTEGER NOT NULL,
    career_start_date DATE NOT NULL,
    team_id BIGINT,
    FOREIGN KEY (team_id) REFERENCES teams (id) ON DELETE SET NULL
) ENGINE=InnoDB;

--rollback DROP TABLE players;
