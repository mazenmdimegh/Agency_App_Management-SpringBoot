package com.bezkoder.springjwt.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Historique {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private LocalDate date;
    private String titre;
    private String status;

    public Historique(String username, LocalDate date, String titre, String status) {
        this.username = username;
        this.date = date;
        this.titre = titre;
        this.status = status;
    }
}
