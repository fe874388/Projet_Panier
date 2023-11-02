package fr.ufrsciencestech.projet.model;

/**
 * FabriqueFruit est un classe qui implement FruitFactory et qui nous sert a crée des fruit
 * Il est la représentation du Design Pattern Fabrique/Factory
 * Il a pour but de simplifier la creation de fruit qui implémente tous l'interface Fruit
 * @author TD2 Groupe 11
 */
public class FabriqueFruit implements FruitFactory{
    
    /**
     * Sert à créer et definir un fruit
     * @param fruit Soit fruit une chaine de caractere, est le nom du fruit
     * @param prix Soit prix est le prix du fruit
     * @param origine Soit origine est le pays d'origine du fruit
     * @return Fruit Le fruit retourné est le fruit crée grâce au paramètre de l'utilisateur
     */
    @Override
    public Fruit creerFruit(String fruit, double prix, String origine) {
        switch(fruit.toLowerCase()) {
            case "banane":
                return new Banane(prix,origine);
            case "orange":
                return new Orange(prix,origine);
            case "fraise":
                return new Fraise(prix,origine);
            case "ananas":
                return new Ananas(prix,origine);
            case "kiwi":
                return new Kiwi(prix,origine);
            case "cerise":
                return new Cerise(prix,origine);
            default:
                System.out.println("Je ne connais pas ce fruit.");
        }
        return null;
    }
}
