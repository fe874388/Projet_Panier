package fr.ufrsciencestech.projet.view;

import fr.ufrsciencestech.projet.view.VueGraphiqueListe;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import fr.ufrsciencestech.projet.model.Fruit;

/**
 * Cette classe représente une boîte de dialogue permettant de boycotter un pays en fonction d'une sélection d'un pays par l'utilisateur
 * L'utilisateur peut choisir quelle pays boycotter en fonction d'un liste de pays.
 * Cette liste de pays est la liste des origines des fruits du catalogue
 * @author TD2 Groupe 11
 */
public class BoycotterPays extends JDialog {
    public VueGraphiqueListe parent;
    public JList<String> jcb;// Liste déroulante des origines des fruits
    public JList<Fruit> fruitList;// Liste des fruits du pays sélectionné
    public JButton boycotterButton;
    public JButton annulerButton;
    
    /**
     * Constructeur de la boîte de dialogue pour boycotter un pays.
     * C'est ici que l'on gère la selection d'un pays à boycotter.
     * Lorsqu'un pays est selectionné et que l'utilisateur clique sur "Boycotter", elle renvoie a l'IHM principale le nom du pays à boycotter
     * @param p La vue graphique parente.
     */
    public BoycotterPays(final VueGraphiqueListe p) {
        super(p, "Boycotter un pays", true);
        this.parent = p;

        setLayout(new BorderLayout());

        DefaultComboBoxModel<Fruit> model = (DefaultComboBoxModel<Fruit>) this.parent.getjComboBox().getModel();
        DefaultComboBoxModel<String> modeleFruitsUniques = new DefaultComboBoxModel<>();
        DefaultListModel<Fruit> modeleListeFruit = new DefaultListModel<>();
        List<String> originesDejaAjoutees = new ArrayList<>();
        for (int i = 0; i < model.getSize(); i++) {
            Fruit fruit = model.getElementAt(i);
            String origine = fruit.getOrigine();
            if (!originesDejaAjoutees.contains(origine)) {
                modeleFruitsUniques.addElement(fruit.getOrigine());
                originesDejaAjoutees.add(origine);
            }
        }
        this.jcb = new JList<>(modeleFruitsUniques);
        jcb.setSelectedIndex(0);
        String selectedOrigine = jcb.getSelectedValue();
        
        for (int i = 0; i < model.getSize(); i++) {
            Fruit fruit = model.getElementAt(i);
            if (selectedOrigine.equals(fruit.getOrigine())) {
                modeleListeFruit.addElement(fruit);
            }
        }
        this.fruitList = new JList<>(modeleListeFruit);
        JScrollPane scrollPane = new JScrollPane(fruitList);
        fruitList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.add(jcb, BorderLayout.WEST);
        this.add(scrollPane, BorderLayout.CENTER);

        boycotterButton = new JButton("Boycotter");
        boycotterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e2) {
                String selectedOrigine = jcb.getSelectedValue();
                parent.boycotterPays(selectedOrigine);
                dispose();
            }
        });

        jcb.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String selectedOrigine = jcb.getSelectedValue();
                    modeleListeFruit.removeAllElements();
                    for (int i = 0; i < model.getSize(); i++) {
                        Fruit fruit = model.getElementAt(i);
                        if (selectedOrigine.equals(fruit.getOrigine())) {
                            modeleListeFruit.addElement(fruit);
                        }
                    }
                }
            }
        });
        annulerButton = new JButton("Annuler");
        annulerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e1) {
                System.out.println("Annulé:");
                dispose(); 
            }
        });
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(boycotterButton);
        buttonPanel.add(annulerButton);
        add(buttonPanel, BorderLayout.SOUTH);
        setSize(450, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
