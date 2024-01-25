package criteres;

import java.util.*;

import gestionLocations.Voiture;

public class InterCritere implements Critere{
	private List<Critere> lesCriteres;

	public InterCritere() {
		lesCriteres = new ArrayList<Critere>();
	}
	
	public void addCritere(Critere c) {
		lesCriteres.add(c);
	}

	@Override
	public boolean estSatisfaitPar(Voiture v) {
		for(Critere c : lesCriteres)
			if(!c.estSatisfaitPar(v))
				return false;
		return true;
	}
}
