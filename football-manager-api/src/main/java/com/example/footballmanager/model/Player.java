package com.example.footballmanager.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "players")
@Getter
@Setter
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    private Integer age;
    @Column(name = "career_start_date")
    private LocalDate careerStartDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Player player = (Player) object;
        return Objects.equals(id, player.id)
                && Objects.equals(firstname, player.firstname)
                && Objects.equals(lastname, player.lastname)
                && Objects.equals(age, player.age)
                && Objects.equals(careerStartDate, player.careerStartDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname, age, careerStartDate);
    }

    @Override
    public String toString() {
        return "Player{"
                + "id=" + id
                + ", firstname='" + firstname + '\''
                + ", lastname='" + lastname + '\''
                + ", age=" + age
                + ", careerStartDate=" + careerStartDate + '}';
    }
}
