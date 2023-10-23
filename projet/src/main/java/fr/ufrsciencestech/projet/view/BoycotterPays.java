/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ufrsciencestech.projet.view;
import fr.ufrsciencestech.projet.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class BoycotterPays extends JDialog {
    private Fruit fruit;
    private VueGraphiqueListe parent;
    private JList<Fruit> jcb;
    
    public BoycotterPays(final VueGraphiqueListe p) {
        super(p, "Boycotter un pays", true);
        this.parent = p;
        
        setLayout(new GridLayout(2, 2));
        
        DefaultComboBoxModel<Fruit> model = (DefaultComboBoxModel<Fruit>) this.parent.getjComboBox().getModel();
        this.jcb = new JList<>(model);
        jcb.setSelectedIndex(0);
        this.add(jcb);
        this.add(new JLabel("", JLabel.CENTER));
        
        JButton boycotterButton = new JButton("Boycotter");
        boycotterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e2) {
                Fruit elementSelectionne = (Fruit) jcb.getSelectedValue();
                //System.out.println("Fruit a supprimeé:"+elementSelectionne.toString());
                parent.Boycottepays(elementSelectionne);
                dispose(); // Ferme la fenêtre de dialogue
            }
        });

        JButton annulerButton = new JButton("Annuler");
        annulerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e1) {
                System.out.println("Annulé:");
                dispose(); // Ferme la fenêtre de dialogue sans créer de fruit
            }
        });

        add(boycotterButton);
        add(annulerButton);
        pack();                // Redimmentionnage automatique
        setSize(400, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
