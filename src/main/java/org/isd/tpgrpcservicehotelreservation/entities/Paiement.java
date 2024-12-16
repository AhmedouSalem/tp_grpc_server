package org.isd.tpgrpcservicehotelreservation.entities;

import javax.persistence.*;
import java.util.Date;



@Entity
public class Paiement {
	@Id
	@GeneratedValue
	private Integer id; // Identifiant unique du paiement
	private Double montant; // Montant payé
	private String modePaiement; // Mode de paiement (ex: carte, espèces)
	private Date datePaiement; // Date du paiement

	@ManyToOne
	@JoinColumn(name = "reservation_id")
	private Reservation reservation; // Réservation associée au paiement

	public Paiement() {
	}

	public Paiement(Double montant, String modePaiement, Date datePaiement, Reservation reservation) {
		super();
		this.montant = montant;
		this.modePaiement = modePaiement;
		this.datePaiement = datePaiement;
		this.reservation = reservation;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getMontant() {
		return montant;
	}

	public void setMontant(Double montant) {
		this.montant = montant;
	}

	public String getModePaiement() {
		return modePaiement;
	}

	public void setModePaiement(String modePaiement) {
		this.modePaiement = modePaiement;
	}

	public Date getDatePaiement() {
		return datePaiement;
	}

	public void setDatePaiement(Date datePaiement) {
		this.datePaiement = datePaiement;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	@Override
	public String toString() {
		return "Paiement [id=" + id + ", montant=" + montant + ", modePaiement=" + modePaiement + ", datePaiement="
				+ datePaiement + ", reservation=" + reservation + "]";
	}

}
