package criteres;

import gestionLocations.Voiture;

public class CritereAnneeProd implements Critere {
	private int annee;

	public CritereAnneeProd(int annee) {
		this.annee = annee;
	}
	
	public boolean estSatisfaitPar(Voiture v) {
		return (v.getAnnee_production()==annee);
	}
}
