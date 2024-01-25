package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;

import criteres.CritereAnneeProd;
import criteres.CritereMarque;
import criteres.CriterePrix;
import criteres.InterCritere;
import gestionLocations.*;
import ui.AjouterVoiture.AnneeEmptyException;
import ui.AjouterVoiture.MarqueEmptyException;
import ui.AjouterVoiture.ModelEmptyException;
import ui.AjouterVoiture.PrixEmptyException;


public class LouerVoiture extends JPanel {
	RendreVoiture RV;
	private JLabel inputs;
	private JTextField marque;
	private JTextField annee;
	private JTextField prix;
	private JButton search;
	private InterCritere IC = new InterCritere();
	JFrame myframe;
	Agence A;
	Container myContent;
	

	public LouerVoiture(JFrame myframe , Agence A) {
	
		this.myframe = myframe;
		this.A = A;
		myContent = myframe.getContentPane();
		

		Font f1 = new Font("Verdana",Font.PLAIN,12);
		Font f2 = new Font("Verdana",Font.BOLD,12);
		
		this.setLayout(new BorderLayout());
		LouerVoiture copy = this;
		
		Iterator<Voiture> I = (A.selectionneL(IC));
	
		//this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		//this.setBorder(new EtchedBorder (Color.BLACK, Color.BLACK));
		
		inputs = new JLabel("Choix des criteres :");
		marque = new JTextField("Enter une marque");
		annee = new JTextField("Enter l'annee de production");
		prix = new JTextField("Enter un prix maximale");
		search = new JButton("Search");
		
		search.setBackground(HomePage.Eerie);
		search.setForeground(Color.WHITE);
		
		//inputs.setSize(new Dimension(300, 100));
		marque.setMaximumSize(new Dimension(340, 30));
		annee.setMaximumSize(new Dimension(340, 30));
		prix.setMaximumSize(new Dimension(340, 30));
		
		marque.setFont(f1);
		annee.setFont(f1);
		prix.setFont(f1);
		
		JPanel P = new JPanel();
		P.setLayout(new BoxLayout(P, BoxLayout.Y_AXIS));
		//P.setBackground(Color.LIGHT_GRAY);
		//P.setAlignmentY(LEFT_ALIGNMENT);
		
		P.add(P.add(Box.createVerticalStrut(15)));
		P.add(inputs);
		P.add(Box.createVerticalStrut(10));
		P.add(marque);
		P.add(P.add(Box.createVerticalStrut(5)));
		P.add(annee);
		P.add(P.add(Box.createVerticalStrut(5)));
		P.add(prix);
		P.add(P.add(Box.createVerticalStrut(10)));
		P.add(search);
		P.setBackground(HomePage.Pale);
		
		
		JPanel VL = new JPanel();
		VL.setLayout(new BoxLayout(VL, BoxLayout.Y_AXIS));
		JPanel columns = new JPanel();
		columns.setMaximumSize(new Dimension(600, 30));
		columns.setBorder(new EtchedBorder (Color.BLACK, Color.WHITE));
		columns.setLayout(new GridLayout(1, 4));
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
		columns.setBackground(HomePage.Olive);
		
		//this.add(Box.createVerticalStrut(15));
		VL.add(Box.createVerticalStrut(25));
		VL.add(columns);
		
		int i=1;
		boolean col = false;
		while(I.hasNext()) {
			Voiture V = (Voiture)I.next();
			JButton Louer = new JButton("  Louer  ");
			Louer.setName(Integer.toString(i++));
			Louer.setForeground(Color.WHITE);
			Louer.setBackground(HomePage.Flame);
			JPanel tmp = new JPanel();
			tmp.setMaximumSize(new Dimension(600, 35));
			tmp.setBorder(new EtchedBorder (Color.BLACK, Color.WHITE));
			tmp.setLayout(new GridLayout(1, 4));
			tmp.add(new JLabel(V.getMarque()));
			tmp.add(new JLabel(V.getModele()));
			tmp.add(new JLabel(Integer.toString(V.getAnnee_production())));
			tmp.add(new JLabel(Integer.toString(V.getPrix())));
			tmp.add(Louer);
			if((col = !col))
				tmp.setBackground(HomePage.Floral);
			else
				tmp.setBackground(Color.WHITE);
			VL.add(tmp);
			
			
			
			Louer.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
					
					//Voiture à louer
					int Vnumber = Integer.parseInt(((JButton)e.getSource()).getName());
					Iterator<Voiture> NI = (A.selectionneL(IC));
					int j=1;
					while(NI.hasNext()) {
						
						if(j == (Vnumber)) {
							Louer(myframe,A,(Voiture)NI.next());
							break;
						}
						j++;

						NI.next();

					}
					
					// this
					removeAll();
					LouerVoiture LV = new LouerVoiture(myframe, A);
					RendreVoiture RVC = new RendreVoiture(A, myframe);
					
					RVC.setLV(LV);
					LV.setRV(RV);
					
					//Updating rendre vioture
					RV.removeAll();
					RV.add(RVC);
					//RV.setLV(LV);
					RV.revalidate();
					RV.repaint();
					
					
					add(LV);
					//LVPanel();
					revalidate();
					repaint();
					
					
					
					/*removeAll();
					add(new LouerVoiture(A));
					revalidate();
					repaint();*/
				}
				
			});
		}
		add("West", P);
		add("Center", VL);
		
		marque.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				if(marque.getText().startsWith("Enter une marque"))
					marque.setText("");
				marque.setBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK));
				marque.setFont(f2);
				
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if(marque.getText().isEmpty()) {
					marque.setFont(f1);
					marque.setText("Enter une marque");
				}
				marque.setBorder(new EtchedBorder (Color.BLACK, Color.WHITE));
			}
			
		});
		
		annee.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				if(annee.getText().startsWith("Enter l'annee de production"))
					annee.setText("");
				annee.setBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK));
				annee.setFont(f2);
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if(annee.getText().isEmpty()) {
					annee.setFont(f1);
					annee.setText("Enter l'annee de production");
				}
				annee.setBorder(new EtchedBorder (Color.BLACK, Color.WHITE));
			}
			
		});
		
		prix.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				if(prix.getText().startsWith("Enter un prix maximale"))
					prix.setText("");
				prix.setBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK));
				prix.setFont(f2);
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if(prix.getText().isEmpty()) {
					prix.setFont(f1);
					prix.setText("Enter un prix maximale");
				}
				prix.setBorder(new EtchedBorder (Color.BLACK, Color.WHITE));
			}
			
		});
		
		search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				try {
					IC = new InterCritere();
					String m, a, p;
					m = marque.getText();
					a = annee.getText();
					p = prix.getText();
					if(!m.startsWith("Enter une marque"))
						IC.addCritere(new CritereMarque(m));
					if(!a.startsWith("Enter l'annee de production"))
						IC.addCritere(new CritereAnneeProd(Integer.parseInt(a)));
					if(!p.startsWith("Enter un prix maximale"))
						IC.addCritere(new CriterePrix(Integer.parseInt(p)));
					
					removeAll();
					//VP = new VoituresPanel(A.selectionne(IC));
					LVPanel();
					//VP.setBackground(Color.WHITE);
					//P.setBackground(HomePage.Pale);
					//CT.setBackground(Color.WHITE);
					//add("West", P);
					//add("North", CT);
					//add("Center", VP);
					//add("Center", VL);
				}catch(NumberFormatException exception){
                    JOptionPane.showMessageDialog(myContent,"Annee et prix doivent etre integers" ,"Fatal error",JOptionPane.ERROR_MESSAGE);;
				}    
				

			}

		});
		
	}
	
	
	 public void LVPanel() {
		   Font f1 = new Font("Verdana",Font.PLAIN,12);
			Font f2 = new Font("Verdana",Font.BOLD,12);
			
			this.setLayout(new BorderLayout());
			LouerVoiture copy = this;
			
			Iterator<Voiture> I = (A.selectionneL(IC));
		
			//this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			//this.setBorder(new EtchedBorder (Color.BLACK, Color.BLACK));
			
			/*inputs = new JLabel("Choix des criteres :");
			marque = new JTextField("Enter une marque");
			annee = new JTextField("Enter l'annee de production");
			prix = new JTextField("Enter un prix maximale");
			search = new JButton("Search");
			
			search.setBackground(HomePage.Eerie);
			search.setForeground(Color.WHITE);
			
			//inputs.setSize(new Dimension(300, 100));
			marque.setMaximumSize(new Dimension(340, 30));
			annee.setMaximumSize(new Dimension(340, 30));
			prix.setMaximumSize(new Dimension(340, 30));
			
			marque.setFont(f1);
			annee.setFont(f1);
			prix.setFont(f1);*/
			
			JPanel P = new JPanel();
			P.setLayout(new BoxLayout(P, BoxLayout.Y_AXIS));
			//P.setBackground(Color.LIGHT_GRAY);
			//P.setAlignmentY(LEFT_ALIGNMENT);
			
			P.add(P.add(Box.createVerticalStrut(15)));
			P.add(inputs);
			P.add(Box.createVerticalStrut(10));
			P.add(marque);
			P.add(P.add(Box.createVerticalStrut(5)));
			P.add(annee);
			P.add(P.add(Box.createVerticalStrut(5)));
			P.add(prix);
			P.add(P.add(Box.createVerticalStrut(10)));
			P.add(search);
			P.setBackground(HomePage.Pale);
			
			
			JPanel VL = new JPanel();
			VL.setLayout(new BoxLayout(VL, BoxLayout.Y_AXIS));
			JPanel columns = new JPanel();
			columns.setMaximumSize(new Dimension(600, 30));
			columns.setBorder(new EtchedBorder (Color.BLACK, Color.WHITE));
			columns.setLayout(new GridLayout(1, 4));
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
			columns.setBackground(HomePage.Olive);
			
			//this.add(Box.createVerticalStrut(15));
			VL.add(Box.createVerticalStrut(25));
			VL.add(columns);
			
			int i=1;
			boolean col = false;
			while(I.hasNext()) {
				Voiture V = (Voiture)I.next();
				JButton Louer = new JButton("  Louer  ");
				Louer.setName(Integer.toString(i++));
				Louer.setForeground(Color.WHITE);
				Louer.setBackground(HomePage.Flame);
				JPanel tmp = new JPanel();
				tmp.setMaximumSize(new Dimension(600, 35));
				tmp.setBorder(new EtchedBorder (Color.BLACK, Color.WHITE));
				tmp.setLayout(new GridLayout(1, 4));
				tmp.add(new JLabel(V.getMarque()));
				tmp.add(new JLabel(V.getModele()));
				tmp.add(new JLabel(Integer.toString(V.getAnnee_production())));
				tmp.add(new JLabel(Integer.toString(V.getPrix())));
				tmp.add(Louer);
				if((col = !col))
					tmp.setBackground(HomePage.Floral);
				else
					tmp.setBackground(Color.WHITE);
				VL.add(tmp);
				
				
				
				Louer.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						
						
						//Voiture à louer
						int Vnumber = Integer.parseInt(((JButton)e.getSource()).getName());
						//List<Voiture> vnl = A.lesVoituresNonLouees();
						Iterator<Voiture> NI = (A.selectionneL(IC));
						int j=1;
						while(NI.hasNext()) {
							
							if(j == (Vnumber)) {
								Louer(myframe,A,(Voiture)NI.next());
								break;
							}
							j++;
							NI.next();

						}
						
						
						// this
						removeAll();
						LouerVoiture LV = new LouerVoiture(myframe, A);
						RendreVoiture RVC = new RendreVoiture(A, myframe);
						
						RVC.setLV(LV);
						LV.setRV(RV);
						
						//Updating rendre vioture
						RV.removeAll();
						RV.add(RVC);
						//RV.setLV(LV);
						RV.revalidate();
						RV.repaint();
						
						
						add(LV);

						LVPanel();
						
						revalidate();
						repaint();
						
						
						
						/*removeAll();
						add(new LouerVoiture(A));
						revalidate();
						repaint();*/
					}
					
				});
			}
			add("West", P);
			add("Center", VL);
			
			/*marque.addFocusListener(new FocusListener() {

				@Override
				public void focusGained(FocusEvent e) {
					// TODO Auto-generated method stub
					if(marque.getText().startsWith("Enter une marque"))
						marque.setText("");
					marque.setBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK));
					marque.setFont(f2);
					
				}

				@Override
				public void focusLost(FocusEvent e) {
					// TODO Auto-generated method stub
					if(marque.getText().isEmpty()) {
						marque.setFont(f1);
						marque.setText("Enter une marque");
					}
					marque.setBorder(new EtchedBorder (Color.BLACK, Color.WHITE));
				}
				
			});
			
			annee.addFocusListener(new FocusListener() {

				@Override
				public void focusGained(FocusEvent e) {
					// TODO Auto-generated method stub
					if(annee.getText().startsWith("Enter l'annee de production"))
						annee.setText("");
					annee.setBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK));
					annee.setFont(f2);
				}

				@Override
				public void focusLost(FocusEvent e) {
					// TODO Auto-generated method stub
					if(annee.getText().isEmpty()) {
						annee.setFont(f1);
						annee.setText("Enter l'annee de production");
					}
					annee.setBorder(new EtchedBorder (Color.BLACK, Color.WHITE));
				}
				
			});
			
			prix.addFocusListener(new FocusListener() {

				@Override
				public void focusGained(FocusEvent e) {
					// TODO Auto-generated method stub
					if(prix.getText().startsWith("Enter un prix maximale"))
						prix.setText("");
					prix.setBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK));
					prix.setFont(f2);
				}

				@Override
				public void focusLost(FocusEvent e) {
					// TODO Auto-generated method stub
					if(prix.getText().isEmpty()) {
						prix.setFont(f1);
						prix.setText("Enter un prix maximale");
					}
					prix.setBorder(new EtchedBorder (Color.BLACK, Color.WHITE));
				}
				
			});
			
			search.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
					try {
						IC = new InterCritere();
						String m, a, p;
						m = marque.getText();
						a = annee.getText();
						p = prix.getText();
						if(!m.startsWith("Enter une marque"))
							IC.addCritere(new CritereMarque(m));
						if(!a.startsWith("Enter l'annee de production"))
							IC.addCritere(new CritereAnneeProd(Integer.parseInt(a)));
						if(!p.startsWith("Enter un prix maximale"))
							IC.addCritere(new CriterePrix(Integer.parseInt(p)));
						
						removeAll();
						//VP = new VoituresPanel(A.selectionne(IC));
						
						//VP.setBackground(Color.WHITE);
						P.setBackground(HomePage.Pale);
						//CT.setBackground(Color.WHITE);
						add("West", P);
						//add("North", CT);
						//add("Center", VP);
						add("Center", VL);
					}catch(NumberFormatException exception){
	                    //JOptionPane.showMessageDialog(myContent,"Annee et prix doivent etre integers" ,"Fatal error",JOptionPane.ERROR_MESSAGE);;
					}    
					

				}

			});*/
	 }
	
	 public void setRV(RendreVoiture RVC) {
     	RV = RVC;
     }
	
	public static void Louer(JFrame myframe,Agence A,Voiture V) {
		
		final JDialog modelDialog = new JDialog(myframe, "Ajouter Client",true);
		modelDialog.setDefaultCloseOperation(
			    JDialog.HIDE_ON_CLOSE);
		Container myContent = modelDialog.getContentPane();
		
		//Declaration;
		JLabel titre,nom,prenom,cin,civilte;
	    JTextField tnom,tprenom,tcin;
	    JComboBox tcivilte;
	    JButton submit,reset;
	    JPanel Form = new JPanel();
	    
	    
	    Font f1 = new Font("Bold",Font.BOLD,22);
        Font f2 = new Font("Bold",Font.BOLD,18);
        //Initialisation
        titre = new JLabel("Ajouter Client");
        nom = new JLabel("Nom     ");
        prenom = new JLabel("Prénom");
        cin = new JLabel("Cin  ");
        civilte = new JLabel("Civilité  ");

        tnom  = new JTextField();
        tnom.setPreferredSize(new Dimension(210, 30));
        tprenom  = new JTextField();
        tprenom.setPreferredSize(new Dimension(210, 30));
        tcin  = new JTextField();
        tcin.setPreferredSize(new Dimension(210, 30));
        String[] civilities = {"M","Mme","MLLe"};
        tcivilte  = new JComboBox(civilities);
        tcivilte.setPreferredSize(new Dimension(210, 30));

        submit = new JButton("Enregistrer");
        submit.setPreferredSize(new Dimension(200, 30));
        reset = new JButton("Reset");
        reset.setPreferredSize(new Dimension(200, 30));
        //Customizing
        titre.setFont(f1);
        nom.setFont(f2);
        prenom.setFont(f2);
        cin.setFont(f2);
        civilte.setFont(f2);
        //
        Font f3 = new Font("Verdana",Font.BOLD,12);
		tnom.setFont(f3);
        tprenom.setFont(f3);
        tcin.setFont(f3);
        reset.setBackground(HomePage.Floral);
        submit.setBackground(HomePage.Eerie);
        submit.setForeground(Color.WHITE);
        //
        /**Add event**/
        tnom.addFocusListener(new ClickFocus());
        tprenom.addFocusListener(new ClickFocus());
        tcin.addFocusListener(new ClickFocus());
        //
        JPanel ptitre = new JPanel();
        ptitre.add(titre,BorderLayout.CENTER);
        //
        JPanel pnom = new JPanel();
        pnom.add(nom,BorderLayout.CENTER);
        pnom.add(pnom.add(Box.createHorizontalStrut(80)));
        pnom.add(tnom,BorderLayout.SOUTH);
        //
        JPanel pprenom = new JPanel();
        pprenom.add(prenom,BorderLayout.CENTER);
        pprenom.add(pprenom.add(Box.createHorizontalStrut(80)));
        pprenom.add(tprenom,BorderLayout.SOUTH);
        //
        JPanel pcin = new JPanel();
        pcin.add(cin,BorderLayout.CENTER);
        pcin.add(pcin.add(Box.createHorizontalStrut(110)));
        pcin.add(tcin,BorderLayout.SOUTH);
        //
        JPanel pcivilte = new JPanel();
        pcivilte.add(civilte,BorderLayout.CENTER);
        pcivilte.add(pcivilte.add(Box.createHorizontalStrut(90)));
        pcivilte.add(tcivilte,BorderLayout.SOUTH);
        //
        JPanel pbuttons = new JPanel();
        pbuttons.add(reset,BorderLayout.CENTER);
        pbuttons.add(pbuttons.add(Box.createHorizontalStrut(20)));
        pbuttons.add(submit,BorderLayout.SOUTH);
        //
        Form = new JPanel();
        Form.setLayout(new BoxLayout(Form, BoxLayout.Y_AXIS));
        Form.add(Form.add(Box.createVerticalStrut(30)));
        Form.add(ptitre);
        Form.add(Form.add(Box.createVerticalStrut(40)));
        Form.add(pnom);
        Form.add(Form.add(Box.createVerticalStrut(10)));
        Form.add(pprenom);
        Form.add(Form.add(Box.createVerticalStrut(10)));
        Form.add(pcin);
        Form.add(Form.add(Box.createVerticalStrut(10)));
        Form.add(pcivilte);
        Form.add(Form.add(Box.createVerticalStrut(10)));
        Form.add(pbuttons);
		//
        submit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
            	String vnom,vprenom,vcin;
        	    Civilite vcivilte;
                try {
                    vnom = tnom.getText();
                    if (vnom.isEmpty())
                        throw new NomEmptyException();
                    vprenom = tprenom.getText();
                    if (vprenom.isEmpty())
                        throw new PrenomEmptyException();
                   
                    vcin = tcin.getText();
                    if (vcin.isEmpty())
                        throw new CinEmptyException();
                    
                    vcivilte =Civilite.valueOf(tcivilte.getSelectedItem().toString());
                    
                    Client cli = new Client(vnom,vprenom,vcin,vcivilte);
                    A.loueVoiture(cli, V);
                    
                    JOptionPane.showMessageDialog(myContent, vnom+" à bien loué "+V.getMarque());
                    modelDialog.dispose();
                }catch(Exception exception){
                    JOptionPane.showMessageDialog(myContent, exception,"Fatal Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               tnom.setText("");
               tprenom.setText("");
               tcin.setText("");
               tcivilte.setSelectedIndex(0);
            }
        });
        
       
	    
        //
        modelDialog.add(Form,BorderLayout.NORTH);
		modelDialog.setSize(500,400);
		modelDialog.setLocation(300, 200);
		modelDialog.setResizable(false);
		modelDialog.setVisible(true);
	}
	
}


class NomEmptyException extends Exception{
    @Override
    public String toString() {
        return "Nom est un champ essentiel!!!ne peut etre vide";
    }
}
class PrenomEmptyException extends Exception{
    @Override
    public String toString() {
        return "Prenom est un champ essentiel!!!ne peut etre vide";
    }
}
class CinEmptyException extends Exception{
    @Override
    public String toString() {
        return "Cin est un champ essentiel!!!ne peut etre vide";
    }
}