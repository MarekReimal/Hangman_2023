package controllers.listeners;

import models.Database;
import models.Model;
import views.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ButtonNew implements ActionListener {
    private final Model model;
    private final View view;

    /**
     * New Game button constructor.
     *
     * @param model    Model
     * @param view     View
     */
    public ButtonNew(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    /**
     * Here is the action that happens when the New Game button is pressed
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
//            JOptionPane.showMessageDialog(null, "Enne alustamist vali kategooria"); // Väljasta teade

        view.hideNewButtons(); // Set access to buttons and text field
        view.getRealDateTime().stop(); // "Stop" real time
        if (!view.getGameTime().isRunning()) { // If gameTime not running
            view.getGameTime().setSeconds(0);
            view.getGameTime().setMinutes(0);
            view.getGameTime().setRunning(true); // Set game running
            view.getGameTime().startTimer(); // Start game time
        } else { // gameTime is running
            view.getGameTime().stopTimer(); // Stop gameTime
            view.getGameTime().setRunning(false); // set game not running
        }
        view.setNewImage(0);
        model.setImageId(0);
        view.getTxtChar().requestFocus(); // After pressing New Game, the input box becomes active
        view.getLblError().setForeground(Color.BLACK); // Vigade info teksti värvus must
        view.getLblError().setText("Wrong 0 letter(s):"); // Algväärtusta vigade info
        model.resetGame(); // Algväärtusta uue mängu jaoks

        // new Database on kirjutatud et pääseda meedotidile ligi, loeb DB-st juhusliku sõna mida kasutaja arvama hakkab,
        // Kaasa valitud kategooria
        new Database(model).selectRandomWord(model.getSelectedCategory());

        // VALMISTAB ETTE KUVAMISEKS
         System.out.println(model.getRandWord()); // TEST TEST NÄITA SÕNA
        List<String> lettersReplaced = Arrays.asList(model.getRandomWordArr());
        Collections.fill(lettersReplaced, "_"); // Võtab sõna ja asendab kõik tähed alakriipsuga
        String lettersReplacedSpacesAdded = String.join(" ", lettersReplaced); // Lisab tähtede vahele tühikud
        view.getLblResult().setText(lettersReplacedSpacesAdded); // GUI label saab väärtuse ja näidatakse graafiliselt
    }
}

//String lettersReplaced = model.getRandomWordArr()[];().replaceAll(".","_");
// https://stackoverflow.com/questions/7318359/how-to-replace-all-characters-in-a-java-string-with-stars
// https://www.baeldung.com/java-convert-string-to-string-array
// teisendab Stringi arrayks mis koosneb tähtedest {"a", "b", "c"}
//String[] lettersReplacedArr = lettersReplaced.split("");

