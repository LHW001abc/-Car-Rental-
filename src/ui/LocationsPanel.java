package ui;

import java.awt.*;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

import gestionLocations.Client;
import gestionLocations.Voiture;

public class LocationsPanel extends JPanel {
	private boolean col;

	public LocationsPanel(Set<Entry<Client, Voiture>> I) {
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		//this.setBorder(new EtchedBorder (Color.BLACK, Color.BLACK));
		
		JPanel mainColumns = new JPanel();
		
		JPanel clientP, voitureP;
		clientP = new JPanel();
		voitureP = new JPanel();
		JLabel client, voiture;
		mainColumns.setMaximumSize(new Dimension(800, 30));
		mainColumns.setBorder(new EtchedBorder (Color.BLACK, Color.WHITE));
		mainColumns.setLayout(new GridLayout(1, 2));
		client = new JLabel("Clients");
		//client.setForeground(Color.WHITE);
		voiture = new JLabel("Voitures");
		clientP.add(client);
		clientP.setBackground(HomePage.Floral);
		voitureP.add(voiture);
		voitureP.setBackground(HomePage.Pale);
		//clientP.setBorder(new EtchedBorder (Color.BLACK, Color.CYAN));
		mainColumns.add(clientP);
		mainColumns.add(voitureP);
		//voiture.setForeground(Color.WHITE);
		//mainColumns.setBackground(Color.DARK_GRAY);
		
		JPanel columns = new JPanel();
		columns.setMaximumSize(new Dimension(800, 30));
		columns.setBorder(new EtchedBorder (Color.BLACK, Color.WHITE));
		columns.setLayout(new GridLayout(1, 8));
		JLabel Nom, Prenom, Cin, Civilite;
		Nom = new JLabel("Nom");
		Nom.setForeground(Color.WHITE);
		Prenom = new JLabel("Prenom");
		Prenom.setForeground(Color.WHITE);
		Cin = new JLabel("CIN");
		Cin.setForeground(Color.WHITE);
		Civilite = new JLabel("Civilite");
		Civilite.setForeground(Color.WHITE);
		columns.add(Nom);
		columns.add(Prenom);
		columns.add(Cin);
		columns.add(Civilite);
		//columns.setBackground(Color.DARK_GRAY);
		
		JLabel Marque, Model, Annee, Prix;
		Marque = new JLabel("Marque");
		Marque.setForeground(Color.WHITE);
		Model = new JLabel("Model");
		Model.setForeground(Color.WHITE);
		Annee = new JLabel("Annee");
		Annee.setForeground(Color.WHITE);
		Prix = new JLabel("Prix");
		Prix.setForeground(Color.WHITE);
		columns.add(Marque);
		columns.add(Model);
		columns.add(Annee);
		columns.add(Prix);
		columns.setBackground(Color.DARK_GRAY);
		
		this.add(Box.createVerticalStrut(15));
		this.add(mainColumns);
		this.add(columns);
		
		for(Map.Entry<Client, Voiture> M : I) {
			
			Client C = (Client)M.getKey();
			Voiture V = (Voiture)M.getValue();
			//GridBagConstraints gbc = new GridBagConstraints();
			JPanel tmp = new JPanel();
			JPanel P1, P2;
			P1 = new JPanel();
			P2 = new JPanel();
			
			P1.setLayout(new GridLayout(1, 4));
			P2.setLayout(new GridLayout(1, 4));
			P1.setMaximumSize(new Dimension(400, 35));
			P2.setMaximumSize(new Dimension(400, 35));
			if(col) {
				P1.setBackground(HomePage.Pale);
				P2.setBackground(HomePage.Floral);
			}else {
				P2.setBackground(HomePage.Pale);
				P1.setBackground(HomePage.Floral);
			}
			col = !col;
			
			tmp.setMaximumSize(new Dimension(800, 35));
			tmp.setBorder(new EtchedBorder (Color.BLACK, Color.WHITE));
			tmp.setLayout(new GridLayout(1, 8));
			
			P1.add(new JLabel(C.getNom()));
			P1.add(new JLabel(C.getPrenom()));
			P1.add(new JLabel(C.getCin()));
			P1.add(new JLabel(C.getCivilite()));
			
			P2.add(new JLabel(V.getMarque()));
			P2.add(new JLabel(V.getModele()));
			P2.add(new JLabel(Integer.toString(V.getAnnee_production())));
			P2.add(new JLabel(Integer.toString(V.getPrix())));
			
			tmp.add(P1);
			tmp.add(P2);
			
			this.add(tmp);
			
		}
			
	}
	
}
