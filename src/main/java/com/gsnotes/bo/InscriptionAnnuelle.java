package com.gsnotes.bo;

import com.gsnotes.utils.export.ExcelExporter;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * Represente une inscription annuelle.
 * 
 * Une inscription annuelle est composée de plusieurs inscriptions dans les
 * matières
 * 

 *
 */

@Entity
public class InscriptionAnnuelle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idInscription;

	private int annee;

	private int etat;

	private String type;

	private int rang;

	private String validation;

	private String mention;

	private String plusInfos;

	/**Permet de stocker les notes des matières*/
	@OneToMany(mappedBy = "inscriptionAnnuelle", cascade = CascadeType.ALL, targetEntity = InscriptionMatiere.class)
	private List<InscriptionMatiere> inscriptionMatieres;

	
	/**Permet de stocker les notes des matières*/
	@OneToMany(mappedBy = "inscriptionAnnuelle", cascade = CascadeType.ALL, targetEntity = InscriptionModule.class)
	private List<InscriptionModule> inscriptionModules;

	@ManyToOne
	@JoinColumn(name = "idNiveau")
	private Niveau niveau;

	@ManyToOne
	@JoinColumn(name = "idEtudiant")
	private Etudiant etudiant;

	public Long getIdInscription() {
		return idInscription;
	}

	public void setIdInscription(Long idInscription) {
		this.idInscription = idInscription;
	}

	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}

	public int getEtat() {
		return etat;
	}

	public void setEtat(int etat) {
		this.etat = etat;
	}

	public Niveau getNiveau() {
		return niveau;
	}

	public void setNiveau(Niveau niveau) {
		this.niveau = niveau;
	}

	public Etudiant getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}

	public List<InscriptionMatiere> getInscriptionMatieres() {
		return inscriptionMatieres;
	}

	public void setInscriptionMatieres(List<InscriptionMatiere> inscriptionMatieres) {
		this.inscriptionMatieres = inscriptionMatieres;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<InscriptionModule> getInscriptionModules() {
		return inscriptionModules;
	}

	public void setInscriptionModules(List<InscriptionModule> inscriptionModules) {
		this.inscriptionModules = inscriptionModules;
	}

	public int getRang() {
		return rang;
	}

	public void setRang(int rang) {
		this.rang = rang;
	}

	public String getValidation() {
		return validation;
	}

	public void setValidation(String validation) {
		this.validation = validation;
	}

	public String getMention() {
		return mention;
	}

	public void setMention(String mention) {
		this.mention = mention;
	}

	public String getPlusInfos() {
		return plusInfos;
	}

	public void setPlusInfos(String plusInfos) {
		this.plusInfos = plusInfos;
	}


}