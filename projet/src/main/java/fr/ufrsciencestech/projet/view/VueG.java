package fr.ufrsciencestech.projet.view;

import fr.ufrsciencestech.projet.controler.Controleur;
import fr.ufrsciencestech.projet.model.Modele;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Observable;
import java.util.Observer;

/**
 * VueG est un classe qui herite de PropertyChangeListener et Observer
 * Il est la représentation du Design Pattern Observable
 * Il a pour but de simplifier la creation de Vue qui implémente toutes les interface graphiques
 * @author TD2 Groupe 11
 */
public interface VueG extends PropertyChangeListener, Observer {
    /**
     * Methode mermettant de gerer des modifications d'attribut du modèle ou du controler.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt);
    
     /**
     * Fonction update initialement utiliser pour gerer le MVC sans Observable
     */
    public void update(Observable m, Object o);
    
    /**
     * Ajoute un ActionListener à certains composants de la Vue pour gérer les interactions utilisateur.
     */
    public void addControleur(Controleur c);
    
}
