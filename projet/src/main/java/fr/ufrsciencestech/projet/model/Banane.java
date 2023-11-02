package fr.ufrsciencestech.projet.model;
/**
 * Banane est un classe qui implement Fruit et qui nous sert d'objet afin de remplir un panier.
 * Banane est composeé d'un attribut Prix et d'un attribut Origine
 * @author TD2 Groupe 11
 */
public class Banane implements Fruit {
    private double prix;
    private String origine;

    /**
     * Constructeur de la classe Banane qui initialise le prix à 0.5€ et l'origine à Espagne
     */
    public Banane() {
        this.prix = 0.5; // prix en euros
        this.origine = "Espagne";
    }
    
    /**
     * Constructeur de la classe Banane qui crée une banane avec les parametres données par l'utilisateur
     * @param prix Le prix de la banane
     * @param origine L'origine de la banane 
     */
    public Banane(double prix, String origine) {
        if (prix < 0)
            this.prix = -prix; // une solution possible pour interdire les prix negatifs
        else
            this.prix = prix;

        if (origine.equals(""))
            this.origine = "Espagne"; // Espagne par défaut
        else
            this.origine = origine;
    }
    
    /**
     * Methode getPrix qui retourne le prix de la banane
     */
    @Override
    public double getPrix() {
        return prix;
    }
    
    /**
     * Methode setPrix qui definie le prix de la banane par un prix en parametre 
     */
    @Override
    public void setPrix(double prix) {
        this.prix = prix;
    }
    
    /**
     * Methode getOrigine qui retourne l'origine de la banane
     */
    @Override
    public String getOrigine() {
        return origine;
    }
    
    /**
     * Methode setOrigine qui definie le pays d'origine de la banane par un prix en parametre 
     */
    @Override
    public void setOrigine(String origine) {
        this.origine = origine;
    }
    
    /**
     * Methode toString qui affiche le fruit et ses attributs origine et prix
     */
    @Override
    public String toString() {
        return "Banane de " + origine + " a " + prix + " €";
    }
    
    /**
     * Methode equals qui compare la banane courant a un autre objet
     * Ce predicat pour tester si deux Bananes sont equivalentes
     */
    @Override
    public boolean equals(Object o) { // predicat pour tester si 2 Bananes sont equivalentes
        if (o != null && getClass() == o.getClass()) {
            Banane ba = (Banane) o;
            return (prix == ba.prix && origine.equals(ba.origine));
        }
        return false;
    }
    
    /**
     * Methode/predicat isSeedless indiquant s'il y a des pepins
     */
    @Override
    public boolean isSeedless() { // predicat indiquant qu'un fruit a des pepins
        return true;
    }

}

 