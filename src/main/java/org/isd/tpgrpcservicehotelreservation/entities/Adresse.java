package org.isd.tpgrpcservicehotelreservation.entities;


import javax.persistence.*;

@Entity
public class Adresse {
	@Id
	@GeneratedValue
	private Integer id;
	private String pays; // Pays
	private String ville; // Ville
	private String rue; // Rue
	private String numero; // Numéro de rue
	private String lieuDit; // Lieu-dit
	private Integer codePostal; // Code postal
	private Integer longitude; // Longitude
	private Integer latitude; // Latitude
	// Relation One-to-One avec l'entité Hotel
	@OneToOne(mappedBy = "adresse") // mappedBy fait référence à l'attribut dans l'entité Hotel
	private Hotel hotel;
	// Relation One-to-One avec l'entité Agence
	@OneToOne(mappedBy = "adresse") // mappedBy fait référence à l'attribut dans l'entité Agence
	private Agence agence;

	public Adresse() {

	}

	public Adresse(String pays, String ville, String rue, String numero, String lieuDit, Integer codePostal,
			Integer longitude, Integer latitude) {
		this.pays = pays;
		this.ville = ville;
		this.rue = rue;
		this.numero = numero;
		this.lieuDit = lieuDit;
		this.codePostal = codePostal;
		this.longitude = longitude;
		this.latitude = latitude;
//		this.hotel = hotel;
//		this.agence = agence;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getLieuDit() {
		return lieuDit;
	}

	public void setLieuDit(String lieuDit) {
		this.lieuDit = lieuDit;
	}

	public Integer getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(Integer codePostal) {
		this.codePostal = codePostal;
	}

	public Integer getLongitude() {
		return longitude;
	}

	public void setLongitude(Integer longitude) {
		this.longitude = longitude;
	}

	public Integer getLatitude() {
		return latitude;
	}

	public void setLatitude(Integer latitude) {
		this.latitude = latitude;
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

	@Override
	public String toString() {
		return "Adresse [id=" + id + ", pays=" + pays + ", ville=" + ville + ", rue=" + rue + ", numero=" + numero
				+ ", lieuDit=" + lieuDit + ", codePostal=" + codePostal + ", longitude=" + longitude + ", latitude="
				+ latitude + "]";
	}

	
}
