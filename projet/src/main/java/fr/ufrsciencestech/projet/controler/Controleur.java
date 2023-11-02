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

/**
 * Le contrôleur gère les interactions utilisateur manipuler un panier de fruits.
 * Il sert à ajouter un fruit au panier, retirer un fruit au panier et permet également de le réinitialiser
 * Il gère egalement la mise à jour de l'interface graphique en fonction des modifications apporté au panier.
 * Il est l'une des représentations du Patron MVC
 * @author TD2 Groupe 11
 */
public class Controleur implements ActionListener {
    private Modele model;
    private VueGraphiqueListe vue;
    private Fruit currentFruit;
    private Panier p = new Panier(10);
    
    /**
     * Constructeur du contrôleur
     * Initialise le modele et la vue en fonction des parametres donnés et ajoute la vue en tant que Listener du model
     * Initialise également le fruit courant par defaut avec le fruit selectionné de la jComboBox
     * @param model Le modèle du panier de fruits.
     * @param vue La vue graphique associée.
     */
    public Controleur(Modele model, VueGraphiqueListe vue){
        this.model = model;
        this.vue = vue;
        model.addPropertyChangeListener(vue);
        currentFruit = (Fruit) vue.getjComboBox().getSelectedItem();
    }
    
    /**
     * Méthode invoquée lorsqu'une action se produit.
     * C'est dans cette fonction que le compteur et le panier sont manipulé en fonction de l'interaction de l'utilisateur avec l'interface graphique
     * @param e L'événement d'action.
     */
    @Override
    public void actionPerformed(ActionEvent e){   //Invoked when an action occurs
        if (e.getActionCommand().equals("RAZP")){
            this.p=new Panier(getPanier().getContenanceMax());
            model.update(2);
            remplirCylindre(vue);
            vue.getjTextArea().setText("Liste des fruit(s) dans mon Panier :\n"+p.toString());
            vue.getAffiche().setText("Fruit(s) total dans le panier  :  0");
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
                //Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
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
    
    /**
     * Définit le panier associé au contrôleur.
     * @param pa Le panier à définir.
     */
    public void setPanier(Panier pa){
        this.p = pa;
    }
    
    /**
     * Obtient le panier associé au contrôleur.
     * @return Le panier associé au contrôleur.
     */
    public Panier getPanier(){
        return this.p;
    }
    
    /**
     * Définit le fruit courant.
     * @param c Le fruit courant à définir.
     */
    public void setcurrentFruit(Fruit c){
        this.currentFruit = c;
    }
    
    /**
     * Obtient le fruit courant.
     * @return Le fruit courant associé au contrôleur.
     */
    public Fruit getcurrentFruit() {
        return currentFruit;
    }

    /**
     * Définit le modèle associé au contrôleur.
     * @param m Le modèle à définir.
     */
    public void setModele(Modele m) {
        this.model = m;
    }
    
    /**
     * Obtient le modele courant.
     * @return Le courant associé au contrôleur.
     */
    public Modele getModele() {
        return this.model;
    }
    
    /**
     * Obtient le vue
     * @return La vue associé au contrôleur.
     */
    public VueGraphiqueListe getVue() {
        return this.vue;
    }

    /**
     * Obtient le modele courant.
     * @param vg La vue à définir.
     */
    public void setVue(VueGraphiqueListe vg) {
        this.vue = vg;
    }

    /**
     * Méthode utilitaire pour trouver un composant JTextArea par son nom dans un conteneur.
     * @param container Le conteneur à parcourir.
     * @param name Le nom du composant à rechercher.
     * @return Le composant JTextArea trouvé, ou null s'il n'est pas trouvé.
     */
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

    /**
     * Remplit la barre de progression du panier dans l'interface graphique.
     * La barre de progression représente la quantité actuelle de fruits dans le panier par rapport sa capacité maximal.
     * @param vue La vue graphique où la barre de progression est affichée.
     */
    public void remplirCylindre(VueGraphiqueListe vue) {
        vue.getPanierProgressBar().setMinimum(0);
        vue.getPanierProgressBar().setMaximum(getPanier().getContenanceMax());

        if (getPanier() != null) {
            int pourcentageRemplissage = (int) ((double) getPanier().getTaillePanier() / vue.getPanierProgressBar().getMaximum() * 100);

            vue.getPanierProgressBar().setValue(getPanier().getTaillePanier());
            vue.getPanierProgressBar().setString(pourcentageRemplissage + "%");
            vue.getPanierProgressBar().setOrientation(JProgressBar.VERTICAL);
            vue.getPanierProgressBar().setForeground(Color.WHITE);

            int nouvelleLargeur = 150; 
            Dimension nouvelleDimension = new Dimension(nouvelleLargeur, vue.getPanierProgressBar().getPreferredSize().height);
            vue.getPanierProgressBar().setPreferredSize(nouvelleDimension);
        }else{
            vue.getPanierProgressBar().setValue(0);
            vue.getPanierProgressBar().setString("0%");
            vue.getPanierProgressBar().setOrientation(JProgressBar.VERTICAL);
            vue.getPanierProgressBar().setForeground(Color.WHITE);

            int nouvelleLargeur = 150;
            Dimension nouvelleDimension = new Dimension(nouvelleLargeur, vue.getPanierProgressBar().getPreferredSize().height);
            vue.getPanierProgressBar().setPreferredSize(nouvelleDimension);
        }
    }
    
    /**
     * Calcule et affiche le prix total du panier dans l'interface graphique.
     * @param vue La vue graphique où le prix total est affiché.
     */
    public void setPrixTotal(VueGraphiqueListe vue){
        double prixTotal = this.getPanier().getPrix();

        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String prixFormater = decimalFormat.format(prixTotal);

        vue.getAffichePrix().setText("Prix Total : " + prixFormater + " €");
    }
    
    /**
     * Affiche une fenêtre d'erreur en cas de panier plein.
     * @param vue La vue graphique où l'erreur est affichée.
     */
    public void afficherErreurPanierPlein(VueGraphiqueListe vue) {
        PanierPlein panierPleinFrame = new PanierPlein("Le panier est plein !");
        panierPleinFrame.setVisible(true);
    }
}


