package fr.ufrsciencestech.projet.view;


import fr.ufrsciencestech.projet.controler.Controleur;
import fr.ufrsciencestech.projet.model.Modele;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

/**
 * Crée une nouvelle instance de VueGraphiqueSimple.
 */
public class VueGraphiqueSimple extends JFrame implements VueG{
    public JButton inc;
    public JButton dec;
    public JLabel affiche;
    
    /**
     * Constructeur de la classe `VueGraphiqueSimple`
     * Initialise et crée des composants pour afficher une fenetre graphique
     */
    public VueGraphiqueSimple(){
        super ("CompteurSwing");
        inc = new JButton("+");
        dec = new JButton("-");
        affiche = new JLabel("0", JLabel.CENTER);
        add(inc, BorderLayout.NORTH);
        add(dec, BorderLayout.SOUTH);
        add(affiche, BorderLayout.CENTER);
        
        inc.setName("Plus");
        dec.setName("Moins");
        affiche.setName("Affichage");
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Associe un contrôleur à cette vue en ajoutant des écouteurs aux boutons d'incrémentation et de décrémentation.
     * @param c Le contrôleur à associer.
     */
    public void addControleur(Controleur c){
        getInc().addActionListener(c);
        getDec().addActionListener(c);
    }
    
    
    //public void update(Observable m, Object compte){     //This method is called whenever the observed object is changed
    //      getAffiche().setText(((Integer) compte).toString());
    //}

    /**
     * Met à jour l'affichage en fonction des modifications dans le modèle.
     * @param evt L'événement de modification de propriété.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt){
        Modele m = (Modele) evt.getSource();
        getAffiche().setText(((Integer)m.getCompteur()).toString());
        
        
    }

    /**
     * @return Le bouton d'incrémentation.
     */
    public JButton getInc() {
        return inc;
    }

    /**
     * @param inc Le bouton d'incrémentation à définir.
     */
    public void setInc(JButton inc) {
        this.inc = inc;
    }

    /**
     * @return Le bouton de décrémentation.
     */
    public JButton getDec() {
        return dec;
    }

    /**
     * @param dec Le bouton de décrémentation à définir.
     */
    public void setDec(JButton dec) {
        this.dec = dec;
    }

    /**
     * @return Le label d'affichage.
     */
    public JLabel getAffiche() {
        return affiche;
    }

    /**
     * @param affiche Le label d'affichage à définir.
     */
    public void setAffiche(JLabel affiche) {
        this.affiche = affiche;
    }
    
    /**
     * @param o Observable 
     * @param arg Object
     */
    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}