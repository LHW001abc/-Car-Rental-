package ui;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

public class ConsultTypes extends JPanel {
	
	private ButtonGroup consultationTypes;
	private JRadioButton voitures;
	private JRadioButton voituresLoue;
	private JRadioButton clients;
	private JRadioButton locations;
	
	public ConsultTypes() {
		this.setMaximumSize(new Dimension(900, 30));
		consultationTypes = new ButtonGroup();
		voitures = new JRadioButton("Touts les voitures");
		voituresLoue = new JRadioButton("Les voitures loue");
		clients = new JRadioButton("Les clients");
		locations = new JRadioButton("Les locations en cours");
		
		this.setLayout(new GridLayout(0,4));
		
		voitures.setSelected(true);
		
		consultationTypes.add(voitures);
		consultationTypes.add(voituresLoue);
		consultationTypes.add(clients);
		consultationTypes.add(locations);
		
		add(voitures);
		add(voituresLoue);
		add(clients);
		add(locations);
		this.setBorder(new EtchedBorder ());
	}

	public JRadioButton getVoitures() {
		return voitures;
	}

	

	public JRadioButton getVoituresLoue() {
		return voituresLoue;
	}

	

	public JRadioButton getClients() {
		return clients;
	}

	
	public JRadioButton getLocations() {
		return locations;
	}

	
	
	
	
}
