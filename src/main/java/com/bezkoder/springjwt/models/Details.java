package com.bezkoder.springjwt.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.xml.soap.Text;
import java.awt.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(	name = "details",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "id_Candidat")
        })
public class Details {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long id_Candidat;
    private String titre;
    private String SiteInternet;
    private String linkedIn;
    private String github;

    public String getLettreMotivation() {
        return lettreMotivation;
    }

    public void setLettreMotivation(String lettreMotivation) {
        this.lettreMotivation = lettreMotivation;
    }

    @Lob
    @Column(name="CONTENT", length=512)
    private String lettreMotivation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_Candidat() {
        return id_Candidat;
    }

    public void setId_Candidat(Long id_Candidat) {
        this.id_Candidat = id_Candidat;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getSiteInternet() {
        return SiteInternet;
    }

    public void setSiteInternet(String siteInternet) {
        SiteInternet = siteInternet;
    }

    public String getLinkedIn() {
        return linkedIn;
    }

    public void setLinkedIn(String linkedIn) {
        this.linkedIn = linkedIn;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }
}
