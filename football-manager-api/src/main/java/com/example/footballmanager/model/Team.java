package com.example.footballmanager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "teams")
@Getter
@Setter
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String country;
    private String city;
    private BigDecimal balance;
    private Float fee;

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Team team = (Team) object;
        return Objects.equals(id, team.id)
                && Objects.equals(name, team.name)
                && Objects.equals(country, team.country)
                && Objects.equals(city, team.city)
                && Objects.equals(balance, team.balance)
                && Objects.equals(fee, team.fee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, country, city, balance, fee);
    }
}
