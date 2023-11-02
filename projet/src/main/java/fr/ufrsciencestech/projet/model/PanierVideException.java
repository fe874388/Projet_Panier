package fr.ufrsciencestech.projet.model;

/**
 * PanierVideException est une Exception qui est appelé lorsque le panier est vide
 * @author TD2 Groupe 11
 */
public class PanierVideException extends Exception {
    /**
     * Constructeur de PanierVideException
     */
    public PanierVideException()
    {
	super("Suppression impossible car le panier est vide !");
    }
}
