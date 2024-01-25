package gestionLocations;

import java.util.Objects;

public class Voiture  {
	private String marque;
	private String modele;
	private int annee_production;
	private int prix;
	
	
	
	public Voiture(String marque, String modele, int annee_production, int prix) {
		this.marque = marque;
		this.modele = modele;
		this.annee_production = annee_production;
		this.prix = prix;
	}
	public String getMarque() {
		return marque;
	}
	public void setMarque(String marque) {
		this.marque = marque;
	}
	public String getModele() {
		return modele;
	}
	public void setModele(String modele) {
		this.modele = modele;
	}
	public int getAnnee_production() {
		return annee_production;
	}
	public void setAnnee_production(int annee_production) {
		this.annee_production = annee_production;
	}
	public int getPrix() {
		return prix;
	}
	public void setPrix(int prix) {
		this.prix = prix;
	}
	
	@Override
	public String toString() {
		return "\tVoiture [marque=" + marque + ", modele=" + modele + ", annee_production=" + annee_production + ", prix="
				+ prix + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(annee_production, marque, modele, prix);
	}
	
	public boolean equals(Voiture V) {
		if (this == V)
			return true;
		if (V == null)
			return false;
		return annee_production == V.annee_production && V.marque.equals(marque)
				&& V.modele.equals(modele) && prix == V.prix;
	}
	
	
}
