/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ufrsciencestech.projet.controler;

import fr.ufrsciencestech.projet.view.*;
import fr.ufrsciencestech.projet.model.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.*;
import java.util.*;
import java.util.List;
import java.util.logging.*;



public class Controleur implements ActionListener {
    private Modele model;
    private VueGraphiqueListe vue;
    private Fruit currentFruit;
    private Panier p = new Panier(10);

    public Controleur(Modele model, VueGraphiqueListe vue){
        this.model = model;
        this.vue = vue;
        model.addPropertyChangeListener(vue);
        currentFruit = (Fruit) vue.getjComboBox().getSelectedItem();
    }
    
    @Override
    public void actionPerformed(ActionEvent e){   //Invoked when an action occurs
        if (e.getActionCommand().equals("RAZP")){
            this.p=new Panier(getPanier().getContenanceMax());
            model.update(2);
            remplirCylindre(vue);
            vue.getjTextArea().setText("Liste des fruit(s) dans mon Panier :\n"+p.toString());
            vue.getAffiche().setText("0");
            vue.getAffichePrix().setText("Prix Total  :  0 €");
        }else if (e.getActionCommand().equals("Retirer")){
            RetirerFruit RetirerFruitDialog = new RetirerFruit(vue,this.p,model);
            setPanier(RetirerFruitDialog.getPanier());
            this.p=RetirerFruitDialog.getPanier();
            remplirCylindre(vue);
            setPrixTotal(vue);
            vue.getjTextArea().setText("Liste des fruit(s) dans mon Panier :\n"+p.toString());
        }else if(e.getActionCommand().equals("AugmenterContenance")){
            this.p.setContenanceMax(this.p.getContenanceMax()+10);
            vue.getPanierProgressBar().setMaximum(this.p.getContenanceMax());
            remplirCylindre(vue);
            setPrixTotal(vue);
            vue.getjTextArea().setText("Liste des fruit(s) dans mon Panier :\n"+p.toString());
        }else if (((Component) e.getSource()).getName().equals("ComboBox")){
            currentFruit = (Fruit) vue.getjComboBox().getSelectedItem();
            System.out.println("**- CHANGEMENT DANS LA COMBOBOX Fruit Courant : " + currentFruit + " -**");
        }
        else if(((Component)e.getSource()).getName().equals("Plus")) {
            try {
                p.ajout(currentFruit);
                model.update(1);
                remplirCylindre(vue);
                setPrixTotal(vue);
                vue.getjTextArea().setText("Liste des fruit(s) dans mon Panier :\n"+p.toString());
            } catch (PanierPleinException ex) {
                afficherErreurPanierPlein(vue);
                Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(((Component)e.getSource()).getName().equals("Moins"))
            try {
                p.retrait();
                model.update(-1);
                remplirCylindre(vue);
                setPrixTotal(vue);
                vue.getjTextArea().setText("Liste des fruit(s) dans mon Panier :\n"+p.toString());
        } catch (PanierVideException ex) {
            Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setPanier(Panier pa){
        this.p = pa;
    }

    public Panier getPanier(){
        return this.p;
    }
    
    public void setcurrentFruit(Fruit c){
        this.currentFruit = c;
    }
    public Fruit getcurrentFruit(){
        return currentFruit;
    }
    public void setModele(Modele m)
    {
        this.model = m;
    }
    public void setVue(VueGraphiqueListe vg)
    {
        this.vue = vg;
    }
    public static JTextArea findJTextAreaByName(Container container, String name) {
        for (Component component : container.getComponents()) {
            if (component instanceof JTextArea && component.getName() != null &&
                    component.getName().equals(name)) {
                return (JTextArea) component;
            } else if (component instanceof Container) {
                JTextArea textArea = findJTextAreaByName((Container) component, name);
                if (textArea != null) {
                    return textArea;
                }
            }
        }
        return null;
    }
/*
    public void remplirCylindre(VueGraphiqueListe v) {
        // Définition des valeurs minimale et maximale
        v.getPanierProgressBar().setMinimum(0);
        v.getPanierProgressBar().setMaximum(getPanier().getContenanceMax()); // Utilisation de la capacité maximale du panier

        if (getPanier() != null) {
            int pourcentageRemplissage = (int) ((double) getPanier().getTaillePanier() / vue.getPanierProgressBar().getMaximum() * 100);

            vue.getPanierProgressBar().setValue(getPanier().getTaillePanier());
            vue.getPanierProgressBar().setString(pourcentageRemplissage + "%");
        } else {
            vue.getPanierProgressBar().setValue(0);
            vue.getPanierProgressBar().setString("0%");
        }
    }
*/
public void remplirCylindre(VueGraphiqueListe v) {
    v.getPanierProgressBar().setMinimum(0);
    v.getPanierProgressBar().setMaximum(getPanier().getContenanceMax());

    if (getPanier() != null) {
        int pourcentageRemplissage = (int) ((double) getPanier().getTaillePanier() / v.getPanierProgressBar().getMaximum() * 100);

        v.getPanierProgressBar().setValue(getPanier().getTaillePanier());
        v.getPanierProgressBar().setString(pourcentageRemplissage + "%");

        // Hypothétiquement, si des méthodes pour définir la couleur et le style existent
        v.setProgressBarColor(Color.GREEN);  // Remplacez Color.GREEN par la couleur souhaitée
        v.setProgressBarStyle("rounded");   // Remplacez "rounded" par le style souhaité
    } else {
        v.getPanierProgressBar().setValue(0);
        v.getPanierProgressBar().setString("0%");

        // Vous pouvez également personnaliser la couleur et le style ici
    }
}

    public void setPrixTotal(VueGraphiqueListe vue){
        double prixTotal = this.getPanier().getPrix();
        
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String prixFormater = decimalFormat.format(prixTotal);

        vue.getAffichePrix().setText("Prix Total : " + prixFormater + " €");
    }

    public void afficherErreurPanierPlein(VueGraphiqueListe vue) {
        PanierPlein panierPleinFrame = new PanierPlein("Le panier est plein !");
        panierPleinFrame.setVisible(true);
    }
}


