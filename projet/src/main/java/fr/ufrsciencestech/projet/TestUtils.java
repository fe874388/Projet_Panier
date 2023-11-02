package fr.ufrsciencestech.projet;

import java.awt.Component;
import java.awt.Container;

/**
 * @author TD2 Groupe 11
 */
public class TestUtils {
    /**
     * Permet de recuperer le composant enfant a partir de son nom et du composant parent
     * @param parent Le composant dit "Parent"
     * @param name Le nom du composant dit "Enfant"
     * @return Le composant enfant
     */
    public static Component getChildNamed(Component parent, String name) {
         //System.out.println("Class: " + parent.getClass() +
         //    " Name: " + parent.getName());
         if (name.equals(parent.getName())) { return parent; }
   
         if (parent instanceof Container) {
            Component[] children = ((Container)parent).getComponents();
   
            for (int i = 0; i < children.length; ++i) {
               Component child = getChildNamed(children[i], name);
               if (child != null) { return child; }
            }
         }
         return null;
    }
}

