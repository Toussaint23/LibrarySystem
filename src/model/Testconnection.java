package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the testconnection database table.
 * 
 */
@Entity
@Table(name="testconnection")
@NamedQuery(name="Testconnection.findAll", query="SELECT t FROM Testconnection t")
public class Testconnection implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false, length=30)
	private String nom;

	@Column(nullable=false, length=30)
	private String prenom;

	public Testconnection() {
	}
	
	

	public Testconnection(int id, String nom, String prenom) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
	}



	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

}