package org.isd.tpgrpcservicehotelreservation.entities;


import javax.persistence.*;

@Entity
public class Login {
	@Id
	@GeneratedValue
	private Integer id;
	private String identifiant; // Identifiant de l'agence
	private String password; // Mot de passe de l'agence
	@OneToOne
	@JoinColumn(name = "id_agence") // Clé étrangère vers Agence
	private Agence agence; // Chaque login appartient à une seule agence

	public Login() {
		// TODO Auto-generated constructor stub
	}

	public Login(String identifiant, String password, Agence agence) {
		this.identifiant = identifiant;
		this.password = password;
		this.agence = agence;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Agence getAgence() {
		return agence;
	}

	public void setAgence(Agence agence) {
		this.agence = agence;
	}

	@Override
	public String toString() {
		return "Login [id=" + id + ", identifiant=" + identifiant + ", password=" + password + ", agence=" + agence
				+ "]";
	}

}
