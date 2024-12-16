package org.isd.tpgrpcservicehotelreservation.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Chambre {
	@Id
	@GeneratedValue
	private Integer id;
	private int nbLit;
	private double prix;
	@ManyToOne
	@JoinColumn(name = "hotel_id", nullable = false)
	private Hotel hotel;
	@OneToMany(mappedBy = "chambre", cascade = CascadeType.ALL)
	private List<Offre> offres;
	private String imageUrl; // URL ou chemin de l'image

	public Chambre() {
	}

	public Chambre(int nbLit, double prix, String imageUrl) {
		this.nbLit = nbLit;
		this.prix = prix;
		this.imageUrl = imageUrl;
		offres = new ArrayList<Offre>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getNbLit() {
		return nbLit;
	}

	public void setNbLit(int nbLit) {
		this.nbLit = nbLit;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public List<Offre> getOffres() {
		return offres;
	}

	public void setOffres(List<Offre> offres) {
		this.offres = offres;
	}
	

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public String toString() {
		return "Chambre [id=" + id + ", nbLit=" + nbLit + ", prix=" + prix + ", hotel=" + hotel + ", offres=" + offres
				+ "]";
	}

}
