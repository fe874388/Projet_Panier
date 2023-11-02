package fr.ufrsciencestech.projet.model;

import fr.ufrsciencestech.projet.view.VueConsole;
import fr.ufrsciencestech.projet.view.VueG;
import fr.ufrsciencestech.projet.view.VueGraphiqueListe;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Observer;

/**
 * Modele est un classe qui herite d'Observable Fruit et qui nous servira au bon fonctionnement de la gestion du Panier
 * Il est l'une des représentations du Patron MVC
 * Modele est composeé d'un attribut compteur 
 * @author TD2 Groupe 11
 */
public class Modele extends Observable{
    private int compteur;   //compteur toujours positif
    PropertyChangeSupport support;

    /**
     * Constructeur Modele
     * Initialisation des attributs de Modele : le compteur de fruit et le PropertyChangeSupport
     */
    public Modele(){
        support = new  PropertyChangeSupport(this);
        compteur = 0;
    }
    
    /**
     * Methode update
     * Elle permet la communication entre les objets observés et les observateurs
     * Met a jour le compteur et reveille les observateurs avec la valeur du compteur 
     * @param incr Valeur -1 ou +1 en fonction de l'interaction de l'utilisateur
     */
    public void update(int incr) {
        if(incr==2){
            this.compteur=0;
        }else{
            int old = this.compteur;
            compteur += incr;
            if(compteur<0){
                compteur=0;
            }
            System.out.println("compteur = "+compteur);
            support.firePropertyChange("value",old,this.compteur);
            setChanged();
            notifyObservers(getCompteur());
        }
    }
    /**
     * Methode 
     * @return Retourne le compteur
     */
    public int getCompteur() {
        return compteur;
    }

    /**
     * Methode addPropertyChangeListener(PropertyChangeListener listener)
     * Methode servant à ajouter un listener
     * @param listener Ecouteur a ajouté
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    
    /**
     * Methode removePropertyChangeListener(PropertyChangeListener listener)
     * Methode servant à retirer un listener
     * @param listener Ecouteur a retirer
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }
    
    /**
     * Methode setCompteur(int newCounter)
     * Methode servant à remplacer la valeur de l'attribut compteur par une autre en parametre 
     * Elle met a jour la nouvelle valeur du compteur et reveille les observateurs avec cette valeur 
     * @param newCounter Nouveau compteur
     */
    public void setCompteur(int newCounter) {
        int old = this.compteur;
        this.compteur = newCounter;
        if(this.compteur<0){
            this.compteur=0;
        }
        support.firePropertyChange("value", old, newCounter);
        setChanged();
        notifyObservers(getCompteur());
    }
}

