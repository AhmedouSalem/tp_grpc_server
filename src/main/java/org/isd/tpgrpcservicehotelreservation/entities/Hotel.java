package org.isd.tpgrpcservicehotelreservation.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;


@Entity
//@Table(name = "SENTIMENT")
public class Hotel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; // Identifiant unique de l'hôtel
	private String nom; // Nom de l'hôtel
	private int etoiles; // Nombre d'étoiles
	// Relation One-to-One avec l'entité Address
	@OneToOne
	@JoinColumn(name = "adresse_id") // Spécifie le nom de la colonne de la clé étrangère
	@JsonIgnore // Empêche la sérialisation JSON de cette relation
	private Adresse adresse; // Adresse de l'hôtel
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "hotel", cascade = CascadeType.ALL)
	@JsonIgnore // Empêche la sérialisation JSON de cette relation
	private List<Chambre> chambres; // Liste des chambres
	@ManyToMany(mappedBy = "hotels", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<Agence> agences; // Liste des agences partenaires pour l'hôtel
	@OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
	private List<Offre> offres = new ArrayList<Offre>(); // Liste des offres proposées par l'hôtel

	public Hotel() {
		// TODO Auto-generated constructor stub
	}

	public Hotel(String nom, int etoiles, Adresse adresse, List<Chambre> chambres, List<Agence> agences) {
		this.nom = nom;
		this.etoiles = etoiles;
		this.adresse = adresse;
		this.chambres = chambres;
		this.agences = agences;
//		this.offres = offres;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getEtoiles() {
		return etoiles;
	}

	public void setEtoiles(int etoiles) {
		this.etoiles = etoiles;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public List<Chambre> getChambres() {
		return chambres;
	}

	public void setChambres(List<Chambre> chambres) {
		this.chambres = chambres;
	}

	public List<Agence> getAgences() {
		return agences;
	}

	public void setAgences(List<Agence> agences) {
		this.agences = agences;
	}

	public List<Offre> getOffres() {
		return offres;
	}

	public void setOffres(List<Offre> offres) {
		this.offres = offres;
	}

	@Override
	public String toString() {
		return "Hotel [id=" + id + ", nom=" + nom + ", etoiles=" + etoiles + ", adresse=" + adresse + ", chambres="
				+ chambres + ", agences=" + agences + ", offres=" + offres + "]";
	}

}
