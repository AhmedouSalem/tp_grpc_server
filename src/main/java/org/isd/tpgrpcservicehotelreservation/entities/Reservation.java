package org.isd.tpgrpcservicehotelreservation.entities;


import javax.persistence.*;
import java.util.Date;
import java.util.List;



@Entity
public class Reservation {
	@Id
	@GeneratedValue
	private Integer id; //
	@ManyToOne//(cascade = CascadeType.ALL)
	@JoinColumn(name = "client_id", nullable = false)
	private Client client; // Client ayant fait la réservation
	@ManyToOne
	@JoinColumn(name = "agence_id")
	private Agence agence; // Agence ayant fait la réservation
	// private Hotel hotel; // Hôtel réservé
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "chambre_id")
	private Chambre chambre; // Chambre réservée
	private Date reservationDate; // Date de la réservation
	private Date dateDeparture; // Date de départ
	@OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
	private List<Paiement> paiements; // Liste des paiements liés à cette réservation

	public Reservation() {
		// TODO Auto-generated constructor stub
	}

	public Reservation(Client client, Agence agence, Chambre chambre, Date reservationDate, Date dateDeparture) {
		super();
		this.client = client;
		this.agence = agence;
		this.chambre = chambre;
		this.reservationDate = reservationDate;
		this.dateDeparture = dateDeparture;
//		this.paiements = paiements;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
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

	public void setChambre(Chambre chambre) {
		this.chambre = chambre;
	}

	public Date getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}

	public Date getDateDeparture() {
		return dateDeparture;
	}

	public void setDateDeparture(Date dateDeparture) {
		this.dateDeparture = dateDeparture;
	}

	public List<Paiement> getPaiements() {
		return paiements;
	}

	public void setPaiements(List<Paiement> paiements) {
		this.paiements = paiements;
	}

	@Override
	public String toString() {
		return "Reservation [id=" + id + ", client=" + client + ", agence=" + agence + ", chambre=" + chambre
				+ ", reservationDate=" + reservationDate + ", dateDeparture=" + dateDeparture + ", paiements="
				+ paiements + "]";
	}

}
