package ui;

import java.awt.Color;
import java.awt.Container;
import java.util.ArrayList;

import javax.swing.*;

import gestionLocations.Agence;
import gestionLocations.Civilite;
import gestionLocations.Client;
import gestionLocations.Voiture;
import gestionLocations.Agence.ClientEstLoueurException;
import gestionLocations.Agence.VoitureEstLoueException;
import gestionLocations.Agence.VoitureNotFoundException;

public class HomePage extends JFrame {
	
	public static Color Pale = new Color(204, 197, 185);
	public static Color Floral = new Color(255, 252, 242);
	public static Color Eerie = new Color(37, 36, 34);
	public static Color Olive = new Color(64, 61, 57);
	public static Color Flame = new Color(235, 94, 40);
	
	private Container myContent;
	private  JTabbedPane header;
	private ConsulterDonnees CD;
	private AjouterVoiture AV;
	private LouerVoiture LV;
	private RendreVoiture RV;
	
	public HomePage(Agence A) {
		super("Agence de voitures");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myContent = this.getContentPane();
		
		CD  = new ConsulterDonnees(A,myContent);
		AV = new AjouterVoiture(A,this);
		RV = new RendreVoiture(A, this);
		LV = new LouerVoiture(this, A);
		
		RV.setLV(LV);
		LV.setRV(RV);
		//RV.setLV(LV);
		AV.setLV(LV);
		AV.setRV(RV);
		

		//myContent.setLayout(getLayout());
		header = new JTabbedPane();
		header.addTab("Consulter les donnees", new JScrollPane(CD));
		header.addTab("Ajouter une voiture", new JScrollPane(AV));
		header.addTab("Louer une voiture", new JScrollPane(LV));
		header.addTab("Rendre une voiture", new JScrollPane(RV));
		myContent.add(header);
		//this.pack();
		this.setSize(900, 550);
		this.setLocation(200, 50);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		

		Voiture v1, v2, v3, v4, v5, v6,v7,v8,v9,v10;
		v1 = new Voiture("BMW", "M7", 2014, 95);
		v2 = new Voiture("Mercedes", "Benz 1.6", 2016, 400);
		v3 = new Voiture("Dacia", "DK", 2012, 90);
		v4 = new Voiture("Maruti", "800 AC", 2007, 90);
		v5 = new Voiture("Renault", "RXT", 2016, 90);
		v6 = new Voiture("Mercedes", "Benz 1.8", 2017, 400);
		v7 = new Voiture("Bugatti", "R9", 2020, 700);
		v8 = new Voiture("Ferrari", "F1", 2017, 500);
		v9 = new Voiture("Audi", "A4", 2015, 300);
		v10 = new Voiture("Uno", "U.1", 2000, 100);

		ArrayList<Voiture> V = new ArrayList<Voiture>();
		V.add(v1);
		V.add(v2);
		V.add(v3);
		V.add(v4);
		V.add(v5);
		V.add(v6);
		V.add(v7);
		V.add(v8);
		V.add(v9);
		V.add(v10);
	
		Agence agence = new Agence(V);
		Client c1, c2, c3;
		c1 = new Client("KABILA", "Anas", "cin1", Civilite.M);
		c2 = new Client("JRAIFY", "Yassine", "cin2", Civilite.M);
		c3 = new Client("ELON", "Musk", "cin3", Civilite.M);
		
		try {
			agence.loueVoiture(c1, v1);
			agence.loueVoiture(c2, v2);
			agence.loueVoiture(c3, v3);
			//System.out.println("hello");
		} catch (VoitureNotFoundException | VoitureEstLoueException | ClientEstLoueurException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//ConsulterDonnees CD = new ConsulterDonnees(CT, agence);
		HomePage myHomePage = new HomePage(agence);
	}
	
}
