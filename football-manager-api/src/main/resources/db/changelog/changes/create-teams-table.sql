--liquibase formatted sql
--changeset denMg:create-teams-table splitStatements:true endDelimiter:;

CREATE TABLE IF NOT EXISTS teams (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    country VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    balance DECIMAL(38, 2) NOT NULL,
    fee FLOAT(3, 1) NOT NULL
) ENGINE=InnoDB;

--rollback DROP TABLE teams;
