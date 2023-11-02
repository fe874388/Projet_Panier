package fr.ufrsciencestech.projet.model;

/**
 * FruitFactory est un interface
 * Il est la représentation du Design Pattern Fabrique/Factory
 * Il a pour but de simplifier la creation de fruit qui implémente tous l'interface Fruit
 * @author TD2 Groupe 11
 */
public interface FruitFactory {
    /**
     * Methode pour créer un fruit
     * @param s Le nom du fruit 
     * @param prix Le prix du fruit
     * @param origine L'origine du fruit
     * @return Un Fruit
     */
    Fruit creerFruit(String s, double prix, String origine);

}
