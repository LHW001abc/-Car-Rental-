package ui;

import gestionLocations.Agence;
import gestionLocations.Voiture;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

class ClickFocus implements FocusListener {

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		((JTextField)e.getSource()).setBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK));
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		((JTextField)e.getSource()).setBorder(new EtchedBorder (Color.BLACK, Color.WHITE));
	}
	
}

public class AjouterVoiture extends JPanel {

    JLabel titre,marque,model,prix,annee;
    JTextField tmarque,tmodel,tprix,tannee;
    JButton submit,reset;
    JPanel Form = new JPanel();
    String vmarque,vmodel;
    int vannee,vprix;
    private LouerVoiture LV;
    private RendreVoiture RV;

    public AjouterVoiture(Agence A,JFrame myContent){
        //super("Ajouter voiture");
        Font f1 = new Font("Bold",Font.BOLD,22);
        Font f2 = new Font("Bold",Font.BOLD,18);
        //Initialisation
        titre = new JLabel("Ajouter nouvelle voiture");
        marque = new JLabel("Marque");
        model = new JLabel("Modèle");
        prix = new JLabel("Prix");
        annee = new JLabel("Annee");
        
        /*titre.setForeground(Color.WHITE);
        marque.setForeground(Color.WHITE);
        model.setForeground(Color.WHITE);
        prix.setForeground(Color.WHITE);
        annee.setForeground(Color.WHITE);*/

        tmarque  = new JTextField();
        tmarque.setPreferredSize(new Dimension(210, 30));
        tmodel  = new JTextField();
        tmodel.setPreferredSize(new Dimension(210, 30));
        tprix  = new JTextField();
        tprix.setPreferredSize(new Dimension(210, 30));
        tannee  = new JTextField();
        tannee.setPreferredSize(new Dimension(210, 30));
        
        /**Add event**/
        tmarque.addFocusListener(new ClickFocus());
        tmodel.addFocusListener(new ClickFocus());
        tprix.addFocusListener(new ClickFocus());
        tannee.addFocusListener(new ClickFocus());
        
        /**Change font**/
        //Font f3 = new Font("Verdana",Font.PLAIN,12);
		Font f3 = new Font("Verdana",Font.BOLD,12);
		tmarque.setFont(f3);
        tmodel.setFont(f3);
        tprix.setFont(f3);
        tannee.setFont(f3);

        submit = new JButton("Enregistrer");
        submit.setPreferredSize(new Dimension(200, 30));
        reset = new JButton("Reset");
        reset.setPreferredSize(new Dimension(200, 30));
        //Customizing
        titre.setFont(f1);
        marque.setFont(f2);
        model.setFont(f2);
        prix.setFont(f2);
        annee.setFont(f2);
        
        //
        JPanel ptitre = new JPanel();
        ptitre.add(titre,BorderLayout.CENTER);
        //
        JPanel pmarque = new JPanel();
        pmarque.add(marque);
        pmarque.add(pmarque.add(Box.createHorizontalStrut(80)));
        pmarque.add(tmarque);
        //
        JPanel pmodel = new JPanel();
        pmodel.add(model);
        pmodel.add(pmodel.add(Box.createHorizontalStrut(80)));
        pmodel.add(tmodel);
        //
        JPanel pprix = new JPanel();
        pprix.add(prix);
        pprix.add(pprix.add(Box.createHorizontalStrut(110)));
        pprix.add(tprix);
        //
        JPanel pannee = new JPanel();
        pannee.add(annee);
        pannee.add(pannee.add(Box.createHorizontalStrut(90)));
        pannee.add(tannee);
        //
        JPanel pbuttons = new JPanel();
        pbuttons.add(reset,BorderLayout.CENTER);
        pbuttons.add(pbuttons.add(Box.createHorizontalStrut(20)));
        pbuttons.add(submit,BorderLayout.SOUTH);
        //
        Form = new JPanel();
        Form.setLayout(new BoxLayout(Form, BoxLayout.Y_AXIS));
        //Borders
        Form.setBorder(new BevelBorder(BevelBorder.RAISED) );
        //submit.setBorder(BorderFactory.createLineBorder(Color.RED));
        //reset.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        //
        Form.add(ptitre);
        Form.add(Form.add(Box.createVerticalStrut(40)));
        Form.add(pmarque);
        Form.add(Form.add(Box.createVerticalStrut(10)));
        Form.add(pmodel);
        Form.add(Form.add(Box.createVerticalStrut(10)));
        Form.add(pprix);
        Form.add(Form.add(Box.createVerticalStrut(10)));
        Form.add(pannee);
        Form.add(Form.add(Box.createVerticalStrut(10)));
        Form.add(pbuttons);
        //Form.add(Form.add(Box.createVerticalStrut(10)));
        //Actions
        
        ///Backgrounds
        this.setBackground(Color.WHITE);
        Form.setBackground(HomePage.Pale);
        ptitre.setBackground(HomePage.Pale);
        pmarque.setBackground(HomePage.Pale);
        pmodel.setBackground(HomePage.Pale);
        pannee.setBackground(HomePage.Pale);
        pprix.setBackground(HomePage.Pale);
        pbuttons.setBackground(HomePage.Pale);
        reset.setBackground(HomePage.Floral);
        //reset.setForeground(Color.WHITE);
        submit.setBackground(HomePage.Eerie);
        submit.setForeground(Color.WHITE);
        
        submit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {

                try {
                    vmarque = tmarque.getText();
                    if (vmarque.isEmpty())
                        throw new MarqueEmptyException();
                    vmodel = tmodel.getText();
                    if (vmodel.isEmpty())
                        throw new ModelEmptyException();
                    if (tprix.getText().isEmpty())
                        throw new PrixEmptyException();
                    vprix = Integer.parseInt(tprix.getText());
                    if (tannee.getText().isEmpty())
                        throw new AnneeEmptyException();
                    vannee = Integer.parseInt(tannee.getText());
                    A.ajouterVoiture(new Voiture(vmarque, vmodel, vannee, vprix));
                    LV.removeAll();
                    RV.removeAll();
                    LouerVoiture LVC = new LouerVoiture(myContent, A);
                    RendreVoiture RVC = new RendreVoiture(A,  myContent);
                    
                    LVC.setRV(RVC);
                    RVC.setLV(LVC);
                    
                    //LV.setRV(RVC);
					LV.add(LVC);
					//RV.setLV(LVC);
					RV.add(RVC);
					LV.revalidate();
					LV.repaint();
					RV.revalidate();
					RV.repaint();

					JOptionPane.showMessageDialog(myContent, "Voiture est ajouté");
                    tmarque.setText("");
                    tmodel.setText("");
                    tprix.setText("");
                    tannee.setText("");
                }catch(NumberFormatException exception){
                    JOptionPane.showMessageDialog(myContent, "Annee et prix doivent etre integers" ,"Fatal error",JOptionPane.ERROR_MESSAGE);
                }catch(Exception exception){
                    JOptionPane.showMessageDialog(myContent, exception ,"Fatal error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               tmarque.setText("");
               tmodel.setText("");
               tprix.setText("");
               tannee.setText("");
            }
        });
        //
        JPanel FormContainer = new JPanel();
        FormContainer.setBackground(Color.WHITE);
        FormContainer.setLayout(new BoxLayout(FormContainer, BoxLayout.Y_AXIS));
        FormContainer.add(FormContainer.add(Box.createVerticalStrut(20)));
        FormContainer.add(Form);
        this.add(FormContainer,BorderLayout.NORTH);
        setSize(600, 300);
        setVisible(true);

    }
    
    public void setLV(LouerVoiture LVC) {
		LV = LVC;
	}
    
    public void setRV(RendreVoiture RVC) {
		RV = RVC;
	}

    class MarqueEmptyException extends Exception{
        @Override
        public String toString() {
            return "Marque est un champ essentiel!!!ne peut etre vide";
        }
    }
    class ModelEmptyException extends Exception{
        @Override
        public String toString() {
            return "Model est un champ essentiel!!!ne peut etre vide";
        }
    }
    class PrixEmptyException extends Exception{
        @Override
        public String toString() {
            return "Prix est un champ essentiel!!!ne peut etre vide";
        }
    }
    class AnneeEmptyException extends Exception{
        @Override
        public String toString() {
            return "Annee est un champ essentiel!!!ne peut etre vide";
        }
    }
    
    /*public static void main(String[] args) {
        VoitureForm2 voitureForm = new VoitureForm2();
    }*/

}



