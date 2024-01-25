package criteres;

import gestionLocations.Voiture;

public class CriterePrix implements Critere {

	private int prix;

	public CriterePrix(int prix) {
		this.prix = prix;
	}
	
	@Override
	public boolean estSatisfaitPar(Voiture v) {
		return (v.getPrix()<=prix);
	}
	
}
