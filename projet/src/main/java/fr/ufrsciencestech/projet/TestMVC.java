/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ufrsciencestech.projet;

import fr.ufrsciencestech.projet.controler.Controleur;
import fr.ufrsciencestech.projet.view.*;
import fr.ufrsciencestech.projet.model.*;

//utilise pour springIoC :
import javax.swing.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author celine
 */
public class TestMVC {
    private VueG vueGraph;      //pour pouvoir changer de vue si on le souhaite
    private Controleur controlSimple;  //pour pouvoir changer de controleur si on le souhaite
    private Panier p;
    private VueConsole vueC;
    
    /**
     * @return the vueg
     */
    public VueG getVueg() {
        return vueGraph;
    }

    /**
     * @param vueg the vueg to set
     */
    public void setVueg(VueG vueg) {
        this.vueGraph = vueGraph;
    }

    /**
     * @return the controleur
     */
    public Controleur getControleur() {
        return controlSimple;
    }

    /**
     * @param controleur the controleur to set
     */
    public void setControleur(Controleur controlSimple) {
        this.controlSimple = controlSimple;
    }
    
    
    public TestMVC(){
        //sans utiliser SpringIoC :
        //vueg = new VueGraphAWT();
        //controleur = new Controleur();
        //controleur.setVue(vueg);
      //  Modele modele = new Modele();
       // VueConsole vuec = new VueConsole();

      //  controleur.setModele(modele);
        
       // modele.addObserver(vueg);        
       // modele.addObserver(vuec);         
       // vueg.addControleur(controleur);
    }
    
    public static void main(String[] args){
      //  TestMVC test = new TestMVC();    //sans utiliser SpringIoC
        
        //La meme chose mais avec SpringIoC :
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        TestMVC test = (TestMVC)context.getBean("MVC");  //SpringIoC
        test.setControleur( (Controleur)context.getBean("Controleur") );  //SpringIoC
        test.setVueg( (VueG)context.getBean("Vue") );   //SpringIoC
         
        Modele m = new Modele();
        test.getControleur().setModele(m);  
        
        m.addObserver(test.getVueg());
        test.getVueg().addControleur(test.getControleur());
        
        VueConsole vuec = new VueConsole();
        m.addObserver(vuec);  
    }
}
