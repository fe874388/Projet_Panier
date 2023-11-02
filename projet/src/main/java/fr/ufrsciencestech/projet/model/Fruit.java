package fr.ufrsciencestech.projet.model;

/**
 * Fruit est un interface
 * Il a pour but de simplifier la creation de fruit qui implémente tous l'interface Fruit
 * @author TD2 Groupe 11
 */
public interface Fruit {
    /**
     * Predicat indiquant si le fruit a ou non des pepins
     * @return True si le fruits n'a pas de pepin sinon False
     */
    public boolean isSeedless();
    
    /**
     * Methode pour definir le prix unitaire du fruit (en euros)
     * @param prix Le prix du fruit
     */
    public void setPrix(double prix);
    
    /**
     * Methode pour recuperer le prix unitaire du fruit (en euros)
     * @return Le prix du fruit
     */
    public double getPrix();
    
    /**
     * Methode pour definir l'origine d'un fruit
     * @param origine L'origine du fruit
     */
    public void setOrigine(String origine);
    
    /**
     * Predicat pour recuperer le pays d'origine du fruit
     * @return L'origine du fruit
     */
    public String getOrigine();
    
    /**
     * Predicat pour tester si 2 fruits sont equivalents
     * @return true s'il sont equivalent et false sinon
     */
    @Override
    public boolean equals(Object o);
    
    /**
     * Methode d'affichage d'un fruit
     * @return Le fruit et ses attributs origine et prix
     */
    @Override
    public String toString();
}
