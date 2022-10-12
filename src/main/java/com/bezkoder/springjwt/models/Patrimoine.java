package com.bezkoder.springjwt.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Patrimoine {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String titre;
	@Lob
	@Column(name="description", length=512)
	private String description;

	private String lieu;
	private String type;
	private String prix;
	private LocalDate date;
	private String image_name;

	@Lob
	private byte[] data;
	public Patrimoine(String titre, String description, String lieu, String type, String prix, LocalDate date,String image_name, byte[] data) {
		this.titre = titre;
		this.description = description;
		this.lieu = lieu;
		this.type = type;
		this.prix = prix;
		this.date = date;
		this.image_name = image_name;
		this.data = data;
	}
	public Patrimoine(String titre, String description, String lieu, String type, String prix, LocalDate date,String image_name) {
		this.titre = titre;
		this.description = description;
		this.lieu = lieu;
		this.type = type;
		this.prix = prix;
		this.date = date;
		this.image_name = image_name;
	}

	@ManyToMany
	public List<User> demandeurs;

	public String getImage_name() {
		return image_name;
	}

	public void setImage_name(String image_name) {
		this.image_name = image_name;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}



	public String getLieu() {
		return lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPrix() {
		return prix;
	}

	public void setPrix(String prix) {
		this.prix = prix;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public List<User> getCandidats() {
		return demandeurs;
	}

	public void setCandidats(List<User> demandeurs) {
		this.demandeurs = demandeurs;
	}
}
