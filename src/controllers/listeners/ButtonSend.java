package controllers.listeners;

import models.Database;
import models.Model;
import views.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


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
        //  Võtab kasutaja sisestatud tähe ja vahetab trükitäheks
        String userChar = this.view.getTxtChar().getText().toUpperCase();
        if (!userChar.trim().isEmpty()) { // Lõikab tühikud, kui ei ole tühi siis ...

            // Võtab ettevalmistatud sõna mida kuvada graafikal,
            String[] wordReadyToShow = model.getWordToShow(userChar);

            // KONTROLL KAS SÕNA ON ÄRA ARVATUD, kui jah, siis peatab aja jne
            if (model.isWordQuessed()) {
                view.showNewButton(); // Set access to buttons and text field
                view.getGameTime().stopTimer(); // Stop gameTime
                view.getGameTime().setRunning(false); // set game not running
                view.getRealDateTime().start(); // Start real time again
                model.setWordQuessed(false); // Taastab kontrolleri väärtuse, et saaks uut mängu käivitada
                String n = JOptionPane.showInputDialog("Sisesta nimi"); // Inputbox
                model.setPlayerName(n); // Paneb mängija nime muutujasse
                // Loob ajatempli
                String strTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                model.setGameTime(strTime); // Salvestab ajatempli scores jaoks
                model.setTimeSeconds(view.getGameTime().getPlayedTimeInSeconds()); // Salvestab mängu kestvuse socores jaoks
                new Database(model).insertScores();
            }
            // KONTROLL KAS ARVATI VALESTI
            if (model.isQuessPassed()) { // Kui on true st arvati valesti siis
                model.setImageId(model.getImageId()+1); // Pildi ID muudetakse
                view.setNewImage(model.getImageId());// Vaheta pilt
                view.getLblError().setText(model.getQuessedWrongChars()); // Kuvab vigade arvu ja valed tähed
                model.setQuessPassed(false); // Taasta kontrolleri väärtus
            }
            // KONTROLL KAS MÄNG ON LÄBI
            if(model.getImageId() == 11) { // Kui on viimane pilt ehk poodud siis
                view.showNewButton(); // Set access to buttons and text field
                view.getGameTime().stopTimer(); // Stop gameTime
                view.getGameTime().setRunning(false); // set game not running
                view.getRealDateTime().start(); // Start real time again
                model.setWordQuessed(false); // Taastab kontrolleri väärtuse, et saaks uut mängu käivitada
                view.getLblResult().setText("L E T ' S  P L A Y"); // GUI label saab väärtuse ja näidatakse graafiliselt
            }
            // KUVAMISE OSA
            if(model.getImageId() == 11) { // Kui on viimane pilt ehk poodud, siis mäng läbi ja tekst lets play
                view.getLblResult().setText("L E T ' S  P L A Y"); // GUI label saab väärtuse ja näidatakse graafiliselt
            } else { // Kuvab sõna mida kasutaja arvab
                String wordReadyToShowSpacesAdded = String.join(" ",wordReadyToShow); // Lisab tähtede vahele tühikud
                view.getLblResult().setText(wordReadyToShowSpacesAdded); // GUI label saab väärtuse ja näidatakse graafiliselt
            }

        }
    }
}
