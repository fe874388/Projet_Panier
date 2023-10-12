/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ufrsciencestech.projet.controler;

import fr.ufrsciencestech.projet.view.*;
import fr.ufrsciencestech.projet.model.*;

import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;



public class Controleur implements ActionListener {
    private Modele m;
    private VueG vg;
    private Panier p;
    
    @Override
    public void actionPerformed(ActionEvent e) {   //Invoked when an action occurs
        if(((Component)e.getSource()).getName().equals("Plus")) {
            try {
                p.ajout();
            } catch (PanierPleinException ex) {
                Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
            }
          m.update(1);  
        }   
        else {
            try {
                m.update(-1);
                p.retrait();
            } catch (PanierVideException ex) {
                Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void setModele(Modele m){
        this.m = m;
    }
    public void setVue(VueG vg){
        this.vg = vg;
    }
    public void setPanier(Panier panier) {
        this.p = panier;
    }
}
