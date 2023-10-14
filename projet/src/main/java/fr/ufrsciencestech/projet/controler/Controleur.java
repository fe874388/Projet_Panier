/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ufrsciencestech.projet.controler;

import fr.ufrsciencestech.projet.view.*;
import fr.ufrsciencestech.projet.model.Modele;
import fr.ufrsciencestech.projet.model.Panier;
import fr.ufrsciencestech.projet.model.Fruit;
import fr.ufrsciencestech.projet.model.PanierPleinException;
import fr.ufrsciencestech.projet.model.PanierVideException;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author celine
 */
public class Controleur implements ActionListener {
    private Modele m;
    private VueG vg;
    private Fruit CurrentFruit;
    private Panier p= new Panier(20);
    
    @Override
    public void actionPerformed(ActionEvent e){   //Invoked when an action occurs
        if (((Component) e.getSource()).getName().equals("ComboBox")){
            JComboBox comboBox = (JComboBox) e.getSource();
            CurrentFruit = (Fruit) comboBox.getSelectedItem();
            System.out.println("**- CHANGEMENT DANS LA COMBOBOX Fruit Courant : " + CurrentFruit + " -**");
        }
        if(((Component)e.getSource()).getName().equals("Plus")) 
            try {
                this.p.ajout(CurrentFruit);
                m.update(1,p);
        } catch (PanierPleinException ex) {
            Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(((Component)e.getSource()).getName().equals("Moins"))
            try {
                p.retrait();
                m.update1(-1,p);
        } catch (PanierVideException ex) {
            Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setPanier(Panier pa){
        this.p = pa;
    }
    
    public void setCurrentFruit(Fruit c){
        this.CurrentFruit = c;
    }
    
    public void setModele(Modele m){
        this.m = m;
    }
    public void setVue(VueG vg){
        this.vg = vg;
    }
}
