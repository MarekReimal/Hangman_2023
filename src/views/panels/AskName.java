package views.panels;

import models.Database;
import models.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Klass loob dialoogiakna mängija nime küsimiseks
 * EI OLE KASUTUSEL
 */

public class AskName extends JFrame implements ActionListener {
    private JTextField txtKasutajaNimi;
    private JButton btnOk;
    private Model model;


    public AskName(Model model){
        this.model = model;
        this.setLayout(new FlowLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 200);
        this.setTitle("Mängija nimi");
        JLabel lblTeave = new JLabel("Palun kirjuta nimi");
        this.add(lblTeave);
        this.txtKasutajaNimi = new JTextField(10);
        this.add(txtKasutajaNimi);
        this.btnOk = new JButton("OK");
        this.btnOk.addActionListener(this);
        this.add(btnOk);
        this.setLocationRelativeTo(null);

    }

    /**
     * Kood mis täidetakse OK nupu vajutamisel
     * @param ae the event to be processed
     */
    public void actionPerformed(ActionEvent ae){
        model.setPlayerName(this.txtKasutajaNimi.getText()); // Kirjutab kasutaja nime modeli muutujasse
       // new Database(model).insertScores();
        this.setVisible(false);

    }


}
