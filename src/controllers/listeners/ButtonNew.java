package controllers.listeners;

import models.Database;
import models.Model;
import views.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonNew implements ActionListener {
    private final Model model;
    private final View view;

    /**
     * New Game button constructor.
     * @param model Model
     * @param view View
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
        view.hideNewButtons(); // Set access to buttons and text field
        view.getRealDateTime().stop(); // "Stop" real time
        if(!view.getGameTime().isRunning()) { // If gameTime not running
            view.getGameTime().setSeconds(0);
            view.getGameTime().setMinutes(0);
            view.getGameTime().setRunning(true); // Set game running
            view.getGameTime().startTimer(); // Start game time
        } else { // gameTime is running
            view.getGameTime().stopTimer(); // Stop gameTime
            view.getGameTime().setRunning(false); // set game not running
        }
        view.setNewImage(0);
        view.getTxtChar().requestFocus(); // After pressing New Game, the input box becomes active
        //String ajut = model.getSelectedCategory();
        new Database(model).selectRandomWord(model.getSelectedCategory());
        System.out.println(model.getRandomWord());
    }
}
