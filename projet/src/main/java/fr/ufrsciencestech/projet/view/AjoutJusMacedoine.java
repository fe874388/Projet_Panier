package fr.ufrsciencestech.projet.view;
import fr.ufrsciencestech.projet.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AjoutJusMacedoine extends JDialog{
    private Fruit fruit;
    private VueGraphiqueListe parent;
    private JList<Fruit> jcb;
    private JRadioButton macedoineRadioButton;
    private JRadioButton jusRadioButton;
    private JPanel jp; 
    
    
    public AjoutJusMacedoine(final VueGraphiqueListe p) {
        super(p, "Ajouter une Macedoine ou un fruit", true);
        this.parent = p;
        
        setLayout(new GridLayout(2, 2));
        
        macedoineRadioButton = new JRadioButton("Macédoine");
        jusRadioButton = new JRadioButton("Jus");
        JPanel jp= new JPanel();
        ButtonGroup group = new ButtonGroup();
        macedoineRadioButton.setSelected(true);
        group.add(macedoineRadioButton);
        group.add(jusRadioButton);
        jp.add(macedoineRadioButton);
        jp.add(jusRadioButton);
        this.add(jp);
        
        DefaultComboBoxModel<Fruit> model = (DefaultComboBoxModel<Fruit>) this.parent.getjComboBox().getModel();
        this.jcb = new JList<>(model);
        jcb.setSelectedIndex(0);
        jcb.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        this.add(jcb);
        
        JButton CreerButton = new JButton("Créer");
        CreerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e2) {
                List<Fruit> selectedFruits = jcb.getSelectedValuesList();
                //System.out.println("Fruit a supprimeé:"+elementSelectionne.toString());
                if(macedoineRadioButton.isSelected()) {
                    Macedoine ma=new Macedoine();
                    for (Fruit fruit : selectedFruits) {
                       ma.ajoute(fruit);
                    }
                    parent.ajouterJusMacedoine(ma);
                }else if (jusRadioButton.isSelected()){
                    Jus jus=new Jus();
                    for (Fruit fruit : selectedFruits) {
                       jus.ajoute(fruit);
                    }
                    parent.ajouterJusMacedoine(jus);
                }
                dispose(); // Ferme la fenêtre de dialogue
            }
        });

        JButton annulerButton = new JButton("Annuler");
        annulerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e1) {
                System.out.println("Annulé");
                dispose(); // Ferme la fenêtre de dialogue sans créer de fruit
            }
        });

        add(CreerButton);
        add(annulerButton);
        pack();                // Redimmentionnage automatique
        setSize(400, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
