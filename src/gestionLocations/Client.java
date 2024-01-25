package gestionLocations;

import java.util.Objects;

/*public enum Civilite {
	M,
	MLLe,
	Mme
}*/

public class Client implements Comparable {
	private String nom;
	private String prenom;
	private String CIN;
	private Civilite civilite; //M, Mlle, Mme
	
	public Client(String nom, String prenom, String CIN, Civilite civilite) {
		this.nom = nom;
		this.prenom = prenom;
		this.CIN = CIN;
		this.civilite = civilite;
	}

	public String getCin() {
		return CIN;
	}
	
	public String getNom() {
		return nom;
	}
	
	public String getPrenom() {
		return prenom;
	}
	
	public String getCivilite() {
		return civilite.toString();
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		Client client = (Client) obj;
		return Objects.equals(CIN, client.CIN);
	}


	@Override
	public int hashCode() {
		return Objects.hash(CIN);
	}

	@Override
	public String toString() {
		return "\tClient [nom=" + nom + ", prenom=" + prenom + ", CIN=" + CIN + ", civilite=" + civilite + "]";
	}



	@Override
	public int compareTo(Object o) {
		int result = this.nom.compareTo(((Client)o).nom);
		if(result != 0)
			return result;
		result = this.prenom.compareTo(((Client)o).prenom);
		if(result != 0)
			return result;
		return this.CIN.compareTo(((Client)o).CIN);
	}


}
