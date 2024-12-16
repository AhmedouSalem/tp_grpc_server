package org.isd.tpgrpcservicehotelreservation.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;


@Entity
public class Agence {
	@Id
	@GeneratedValue
	private Integer id;
	private String code;
	private String nom;
	@OneToOne
	@JoinColumn(name = "adresse_id")
	private Adresse adresse;
	@OneToOne(mappedBy = "agence", cascade = CascadeType.ALL)
	private Login login;
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "HOTEL_AGENCE_MAPPING", joinColumns = @JoinColumn(name = "agence_id"), inverseJoinColumns = @JoinColumn(name = "hotel_id"))
	@JsonIgnore
	private List<Hotel> hotels = new ArrayList<>();
	@OneToMany(mappedBy = "agence", cascade = CascadeType.ALL)
	private List<Offre> offres;
	@OneToMany(mappedBy = "agence", cascade = CascadeType.ALL)
	private List<Reservation> reservations;

	public Agence() {
		// TODO Auto-generated constructor stub
	}

	public Agence(String code, String nom, Adresse adresse, Login login) {
		this.code = code;
		this.nom = nom;
		this.adresse = adresse;
		this.login = login;
//		this.hotels = hotels;
//		this.offres = offres;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public List<Hotel> getHotels() {
		return hotels;
	}

	public void setHotels(List<Hotel> hotels) {
		this.hotels = hotels;
	}

	public List<Offre> getOffres() {
		return offres;
	}

	public void setOffres(List<Offre> offres) {
		this.offres = offres;
	}

	@Override
	public String toString() {
		return "Agence [id=" + id + ", code=" + code + ", nom=" + nom + ", adresse=" + adresse + ", login=" + login
				+ ", hotels=" + hotels + ", offres=" + offres + "]";
	}

}
