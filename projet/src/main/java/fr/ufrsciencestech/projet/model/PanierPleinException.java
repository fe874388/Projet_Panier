package fr.ufrsciencestech.projet.model;

/**
 * PanierPleinException est une Exception qui est appelé lorsque le panier est plein
 * @author TD2 Groupe 11
 */
public class PanierPleinException extends Exception {
    /**
     * Constructeur de PanierPleinException
     */
    public PanierPleinException()
    {
	super("Ajout impossible car le panier est plein !");
    }

}
