package controllers.listeners;

import models.Model;
import views.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonSend implements ActionListener {

    private Model model;
    private View view;

    public ButtonSend(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    /**
     * Koodiosa mis täidetakse kui vajutatakse Send nuppu
     * @param EventBtnSendPressed the event to be processed
     */
    public void actionPerformed(ActionEvent EventBtnSendPressed) {

        String userChar = this.view.getTxtChar().getText().toUpperCase(); // Sisestatud täht võetakse . suureks täheks
        if (!userChar.trim().isEmpty()) { // võtab muutuja, lõikab tühikud, kui ei ole tühi siis ...
            System.out.println(userChar); // Test

        }



        //System.out.println(userChar); // Test
    }
}
