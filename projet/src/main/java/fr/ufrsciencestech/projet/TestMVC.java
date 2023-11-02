package fr.ufrsciencestech.projet;

import fr.ufrsciencestech.projet.controler.Controleur;
import fr.ufrsciencestech.projet.view.*;
import fr.ufrsciencestech.projet.model.*;
import javax.swing.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.Observable;

/**
 * Classe TestMVC qui nous servira de programme lanceur (main) pour ce Projet
 * @author TD2 GROUPE 11
 */
public class TestMVC {
    private VueG vueg; //test
    private Controleur controleur;

    /**
     * Sert a obtenir une vue
     * @return VueG Soit la vue qui est en train d'être utilisé
     */
    public VueG getVueg() {
        return vueg;
    }
    
    /**
     * Sert à definir notre vue courante en la remplacant par une autre
     * @param vueg Soit vueg la nouvelle vue
     */
    public void setVueg(VueG vueg) {
        this.vueg = vueg;
    }

    /**
     * Sert a obtenir un controler
     * @return Controleur Soit le controleur qui est en train d'être utilisé
     */
    public Controleur getControleur() {
        return controleur;
    }

    /**
     * Sert à definir notre controleur courant en le remplacant par un autre
     * @param controleur Soit controleur le nouveau controleur
     */
    public void setControleur(Controleur controleur) {
        this.controleur = controleur;
    }

     /**
     * Le main sert à initialiser tout les differents élements nécessaire au bon fonctionnement de notre projet et à les lancer/éxécuter
     * @param args Par default
     * @throws PanierPleinException Si le panier est plein
     */
    public static void main(String[] args) throws PanierPleinException {
        //TestMVC test = new TestMVC();    //sans utiliser SpringIoC

        //La meme chose mais avec SpringIoC :
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        TestMVC test = (TestMVC)context.getBean("MVC");  //SpringIoC
        test.setControleur( (Controleur)context.getBean("Controleur") );  //SpringIoC


        test.setVueg( (VueG)context.getBean("Vue") );   //SpringIoC

        test.getControleur().setModele((Modele)context.getBean("Modele"));

        test.getVueg().addControleur(test.getControleur());
    }
}
