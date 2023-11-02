package fr.ufrsciencestech.projet.view;

import fr.ufrsciencestech.projet.controler.Controleur;
import fr.ufrsciencestech.projet.model.Modele;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Observable;
import java.util.Observer;

/**
 * La classe `VueConsole` représente une vue de l'application qui affiche les informations dans la console.
 * Elle implémente l'interface `PropertyChangeListener` pour écouter les changements dans le modèle.
 */
public class VueConsole implements PropertyChangeListener, Observer{
    public String trace;// Trace à afficher dans la console
    public Modele model = new Modele();// Modèle associé à cette vue

    /**
     * Récupère la trace actuellement affichée dans la console.
     * @return La trace actuelle
     */
    public String getTrace() {
        return trace;
    }

    /**
     * Définit la trace à afficher dans la console.
     * @param trace La nouvelle trace
     */
    public void setTrace(String trace) {
        this.trace = trace;
    }

    /**
     * Constructeur de la classe `VueConsole`.
     * Initialise la trace avec une valeur par défaut.
     */
    public VueConsole(){
        trace = "Valeur initiale du panier : " + 0;
        System.out.println(trace);
    }

    /**
     * Met à jour la vue en tant qu'observateur en réponse aux changements dans le modèle.
     * @param m Le modèle observé
     * @param compteur La nouvelle valeur du compteur dans le modèle
     */
    public void update(Observable m, Object compteur){   // Cette méthode est appelée chaque fois que l'objet observé (le modèle) est modifié.
        // Ici, vous pouvez mettre à jour la vue en fonction des nouvelles données reçues.
        //  ((Modele) m).setCompteur((Integer)compteur);
    }

    /**
     * Méthode de l'interface PropertyChangeListener, appelée lorsqu'une propriété du modèle change.
     * @param evt L'événement de changement de propriété
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt){
        Modele m = (Modele) evt.getSource();
        // Mise à jour de la trace avec la nouvelle valeur du compteur du modèle.
        trace = "Nouvelle valeur : " + m.getCompteur();
        System.out.println(trace);
        // Affiche les détails du changement de propriété.
        System.out.println("Variation of " + evt.getPropertyName());
	    System.out.println("\t(" + evt.getOldValue() +" -> " + evt.getNewValue() + ")");
	    System.out.println("Property in object " + evt.getSource());
    }

}
