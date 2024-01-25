package ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

import gestionLocations.Agence;
import gestionLocations.Client;
import gestionLocations.Voiture;

public class RendreVoiture extends JPanel {
	private boolean col;
	private LouerVoiture LV;
	
	public RendreVoiture(Agence A, JFrame myContent) {
		
		RendreVoiture copy = this;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		//this.setBorder(new EtchedBorder (Color.BLACK, Color.BLACK));
		
		JPanel mainColumns = new JPanel();
		
		JPanel clientP, voitureP, videP;
		clientP = new JPanel();
		voitureP = new JPanel();
		videP = new JPanel();
		
		videP.setSize(new Dimension(50, 30));
		
		JLabel client, voiture;
		mainColumns.setMaximumSize(new Dimension(800, 30));
		mainColumns.setBorder(new EtchedBorder (Color.BLACK, Color.WHITE));
		mainColumns.setLayout(new BoxLayout(mainColumns, BoxLayout.X_AXIS));
		client = new JLabel("Clients");
		//client.setForeground(Color.WHITE);
		voiture = new JLabel("Voitures");
		clientP.add(client);
		clientP.setBackground(HomePage.Floral);
		voitureP.add(voiture);
		voitureP.setBackground(HomePage.Pale);
		videP.setLayout(new BoxLayout(videP, BoxLayout.X_AXIS));
		//clientP.setBorder(new EtchedBorder (Color.BLACK, Color.CYAN));
		JButton rendre1 = new JButton("Rendre");
		rendre1.setVisible(false);
		videP.add(new JLabel("                            "));
		mainColumns.add(clientP);
		mainColumns.add(voitureP);
		mainColumns.add(videP);
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
		columns.add(new JPanel());
		columns.setBackground(Color.DARK_GRAY);
		
		this.add(Box.createVerticalStrut(15));
		this.add(mainColumns);
		this.add(columns);
		
		Set<Entry<Client, Voiture>> I = A.lesLocations();
		for(Map.Entry<Client, Voiture> M : I) {
			
			Client C = (Client)M.getKey();
			Voiture V = (Voiture)M.getValue();
			//GridBagConstraints gbc = new GridBagConstraints();
			JPanel tmp = new JPanel();
			JPanel P1, P2, P3;
			P1 = new JPanel();
			P2 = new JPanel();
			P3 = new JPanel();
			
			P1.setLayout(new GridLayout(1, 4));
			P2.setLayout(new GridLayout(1, 4));
			//P3.setLayout(new BoxLayout(P3, BoxLayout.X_AXIS));
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
			tmp.setLayout(new BoxLayout(tmp, BoxLayout.X_AXIS));
			//tmp.setLayout(new GridLayout(1, 3));
			
			P1.add(new JLabel(C.getNom()));
			P1.add(new JLabel(C.getPrenom()));
			P1.add(new JLabel(C.getCin()));
			P1.add(new JLabel(C.getCivilite()));
			
			P2.add(new JLabel(V.getMarque()));
			P2.add(new JLabel(V.getModele()));
			P2.add(new JLabel(Integer.toString(V.getAnnee_production())));
			P2.add(new JLabel(Integer.toString(V.getPrix())));
			
			JButton rendre = new JButton("  Rendre  ");
			rendre.setName(C.getCin());
			rendre.setSize(new Dimension(50, 35));
			rendre.setForeground(Color.WHITE);
			rendre.setBackground(HomePage.Flame);
			//P3.setSize(new Dimension(200, 35));
			P3.add(rendre);
			P3.setLayout(new BoxLayout(P3, BoxLayout.X_AXIS));
			
			tmp.add(P1);
			tmp.add(P2);
			tmp.add(P3);
			
			this.add(tmp);
			
			rendre.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					A.rendVoiture(A.findClient(((JButton)e.getSource()).getName()));
					LV.removeAll();
					removeAll();
					LouerVoiture LVC = new LouerVoiture(myContent, A);
					RendreVoiture RV = new RendreVoiture(A, myContent);
					
					LVC.setRV(RV);
					RV.setLV(LV);
					
					LV.add(LVC);
					//LV.setRV(RV);
					LV.revalidate();
					LV.repaint();
					
					
					
					add(RV);
					revalidate();
					repaint();
					/*tmp.add(P1);
					tmp.add(P2);
					tmp.add(P3);
					
					add(tmp);*/
				}
				
			});
			
			
		}
	}
	
	public void setLV(LouerVoiture LVC) {
		LV = LVC;
	}
}
