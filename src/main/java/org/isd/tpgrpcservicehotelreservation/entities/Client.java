package org.isd.tpgrpcservicehotelreservation.entities;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Client {
	@Id
	@GeneratedValue
	private Integer id;
	private String prenom; // Prénom du client
	private String nom; // Nom du client
	private String email; // Email du client
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "client", cascade = CascadeType.ALL)
	private List<Reservation> reservations = new ArrayList<Reservation>();

	public Client() {
		// TODO Auto-generated constructor stub
	}

	public Client(String prenom, String nom, String email) {
		super();
		this.prenom = prenom;
		this.nom = nom;
		this.email = email;
//		this.reservations = reservations;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", prenom=" + prenom + ", nom=" + nom + ", email=" + email + ", reservations="
				+ reservations + "]";
	}

}
