package fr.ufrsciencestech.projet.model;

/**
 *
 * @author idira
 */
public class FabriqueFruit implements FruitFactory{
    @Override
    public Fruit creerFruit(String fruit, double prix, String origine) {
        switch(fruit.toLowerCase()) {
            case "banane":
                return new Banane();
            case "orange":
                return new Orange();
            case "fraise":
                return new Fraise();
            case "ananas":
                return new Ananas();
            case "kiwi":
                return new Kiwi();
            case "cerise":
                return new Cerise();
            default:
                System.out.println("Je ne connais pas ce fruit.");
        }
        return null;
    }
    
}
