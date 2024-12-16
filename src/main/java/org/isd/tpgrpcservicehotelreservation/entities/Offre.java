package org.isd.tpgrpcservicehotelreservation.entities;

import java.util.Date;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Offre {
	@Id
	@GeneratedValue
	private Integer id; // Identifiant unique de l'offre
	private Double tarifAgence; // Tarif proposé à l'agence
	private Date dateDebut; // Date de début de l'offre
	private Date dateFin; // Date de fin de l'offre

	@ManyToOne
	@JoinColumn(name = "hotel_id")
	private Hotel hotel; // Hôtel proposant l'offre

	@ManyToOne
	@JoinColumn(name = "agence_id")
	private Agence agence; // Agence recevant l'offre

	@ManyToOne
	@JoinColumn(name = "chambre_id")
	private Chambre chambre; // Chambre concernée par l'offre
	
	// Nouveaux attributs
    private boolean isUsed;    // Offre utilisée

	public Offre() {
	}

	public Offre(Double tarifAgence, Date dateDebut, Date dateFin, Hotel hotel, Agence agence, Chambre chambre) {
		this.tarifAgence = tarifAgence;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.hotel = hotel;
		this.agence = agence;
		this.chambre = chambre;
		isUsed = false;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getTarifAgence() {
		return tarifAgence;
	}

	public void setTarifAgence(Double tarifAgence) {
		this.tarifAgence = tarifAgence;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Agence getAgence() {
		return agence;
	}

	public void setAgence(Agence agence) {
		this.agence = agence;
	}

	public Chambre getChambre() {
		return chambre;
	}
	
	
	public boolean isUsed() {
		return isUsed;
	}

	public void setUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}

	public void setChambre(Chambre chambre) {
		this.chambre = chambre;
	}

	@Override
	public String toString() {
		return "Offre [id=" + id + ", tarifAgence=" + tarifAgence + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin
				+ ", hotel=" + hotel + ", agence=" + agence + ", chambre=" + chambre + "]";
	}
}
