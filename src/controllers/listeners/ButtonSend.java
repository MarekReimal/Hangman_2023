package controllers.listeners;

import models.Model;
import views.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ButtonSend implements ActionListener {

    private Model model;
    private View view;
    private int x = 0; // piltide loendur

    public ButtonSend(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    /**
     * Koodiosa mis täidetakse kui vajutatakse Send nuppu
     * @param EventBtnSendPressed the event to be processed
     */
    public void actionPerformed(ActionEvent EventBtnSendPressed) {
        //  võtab kasutaja tähe vormilt ja vahetab trükitäheks
        String userChar = this.view.getTxtChar().getText().toUpperCase();
        if (!userChar.trim().isEmpty()) { // võtab muutuja, lõikab tühikud, kui ei ole tühi siis ...

            System.out.println(userChar); // Test prindi sisestatud täht terminali

            String[] wordReadyToShow = model.getWordToShow(userChar); // võtab ettevalmistatud sõna mida kuvada graafikal, vajalik String[]
            System.out.println("kas tagastus sõna mida näidata " + String.join("",wordReadyToShow)); // Test

            // Kontroll kas sõna on arvatud, kui jah, siis peatab aja jne
            if (model.isWordQuessed()) {
                view.showNewButton(); // Set access to buttons and text field
                view.getGameTime().stopTimer(); // Stop gameTime
                view.getGameTime().setRunning(false); // set game not running
                view.getRealDateTime().start(); // Start real time again
                model.setWordQuessed(false); // Taastab kontrolleri väärtuse, et saaks uut mängu käivitada
            }

            // Kontroll kas arvati õigesti või valesti
            if (model.isMistakeQuess()) { // Kui on true st arvati valesti siis
                view.setNewImage(this.x++);// vaheta pilt
                model.setMistakeQuess(false);
            }



            // KUVAMISE OSA
            String wordReadyToShowSpacesAdded = String.join(" ",wordReadyToShow); // Lisab tähtede vahele tühikud
            view.getLblResult().setText(wordReadyToShowSpacesAdded); // GUI label saab väärtuse ja näidatakse graafiliselt

            //if(model.getRandomWord()==)  // Juhusliku sõna ja kasutaja sõna võrdlus, kas sõna on ära arvatud
            // testi String[] võrdlemist ja ka saab lahti modelis ühest muutujast randomWord, jääks ainult Arr

        }



        //System.out.println(userChar); // Test
    }
}
