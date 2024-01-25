package gestionLocations;

import java.util.*;
import java.util.Map.Entry;

import criteres.Critere;



public class Agence {
	private List<Voiture> voitures;
	private Map<Client, Voiture> Locations;

	public Agence() {
		voitures = new ArrayList<Voiture>();
		Locations = new TreeMap<Client, Voiture>();
	}
	
	
	
	public Agence(ArrayList<Voiture> voitures) {
		this.voitures = new ArrayList<Voiture>(voitures);
		Locations = new TreeMap<Client, Voiture>();
	}
	
	public  Set<Entry<Client, Voiture>> lesLocations(){
		return Locations.entrySet();
	}
	
	
	
	public Iterator<Voiture> selectionneL(Critere c) {
		ArrayList<Voiture> L = new ArrayList<Voiture>();
		for(Voiture v : lesVoituresNonLouees())
			if(c.estSatisfaitPar(v))
				L.add(v);
		return L.iterator();
	}
	
	public Iterator<Voiture> selectionne(Critere c) {
		ArrayList<Voiture> L = new ArrayList<Voiture>();
		for(Voiture v : voitures)
			if(c.estSatisfaitPar(v))
				L.add(v);
		return L.iterator();
	}
	
	public void afficheSelection(Critere c) {
		Iterator<Voiture> I = selectionne(c);
		while(I.hasNext()) {
			System.out.println("\t" + I.next());
		}
	}
	
	public void ajouterVoiture(Voiture v) 
		throws Exception
	{
		if(voitureExist(v))
			throw new Exception() {
				public String toString() {
					return "Voiture déja existe!!!!!!!!!!!!!!";
				}
			};
			
		voitures.add(new Voiture(v.getMarque(), v.getModele(), v.getAnnee_production(), v.getPrix()));
	}
	
	public void loueVoiture(Client client, Voiture v) throws VoitureNotFoundException, VoitureEstLoueException, ClientEstLoueurException {
		if(!voitureExist(v))
			throw new VoitureNotFoundException();
		if(estLoueur(client))
			throw new ClientEstLoueurException();
		if(estLoue(v))
			throw new VoitureEstLoueException();
		Locations.put(client, v);
	}
	
	public boolean voitureExist(Voiture V) {
		for(Voiture voiture : voitures)
			if(voiture.equals(V))
				return true;
		return false;
	}
	
	/*public boolean estLoueur(Client client) {
		return Locations.containsKey(client);
	}*/
	
	public boolean estLoue(Voiture v) {
		return Locations.containsValue(v);
	}
	
	public boolean estLoueur(Client client) {
		Iterator I = Locations.keySet().iterator();
		while(I.hasNext()) {
			if(((Client)I.next()).getCin().toUpperCase().equals(client.getCin().toUpperCase()))
				return true;
		}
		return false;
	}
	
	public void rendVoiture(Client client) {
		if(estLoueur(client))
			Locations.remove(client);
	}
	
	public Iterator<Voiture> lesVoituresLouees() {
		return Locations.values().iterator();
	}
	
	public List<Voiture> lesVoituresNonLouees(){
        List<Voiture> voitureList = new ArrayList<>();
        for( Voiture voiture : voitures )
            if(!estLoue(voiture))
                voitureList.add(voiture);
        return voitureList;
    }
	
	public Iterator<Client> lesClients(){
		return Locations.keySet().iterator();
	}
	
	public Client findClient(String CIN) {
		for(Map.Entry<Client, Voiture> M : Locations.entrySet())
			if(M.getKey().getCin().equals(CIN))
				return M.getKey();
		return null;
	}

	@Override
	public String toString() {
		return "Agence [\n   voitures=" + voitures + ",\n   Locations=" + Locations + "\n]";
	}
	
	public void afficherLocations() {
		for(Map.Entry<Client, Voiture> M : Locations.entrySet())
			System.out.println(M.getKey() + " ==> " + M.getValue());
	}
	
	
	
	public void afficherVoitures(Critere C) {
		for(Voiture V : voitures)
			if(C.estSatisfaitPar(V))
				System.out.println("\t" + V);
	}

	public class VoitureEstLoueException extends Exception{
	    @Override
	    public String toString() {
	        return "La voiture est deja loue par un client";
	    }
	}
	
	public class ClientEstLoueurException extends Exception{
	    @Override
	    public String toString() {
	        return "Ce client loue actuellement une voiture, il ne peut pas loue plus qu'une voiture a la fois";
	    }
	}
	
	public class VoitureNotFoundException extends Exception{
	    @Override
	    public String toString() {
	        return "La voiture n'existe pas dans l'agence";
	    }
	}
	
	
}
