package controllers;

import controllers.listeners.*;
import models.Model;
import views.View;

public class Controller {
    // private final Model model;
    // private final View view;

    public Controller(Model model, View view) {
        // this.model = model;
        // this.view = view;

        view.registerButtonScores(new ButtonScores(model, view)); // Make a Leaderboard ActionListener for the button
        view.registerButtonNew(new ButtonNew(model, view)); // Make a New Game ActionListener for the button
        view.registerButtonCancel(new ButtonCancel(model, view)); // Make a Cancel game ActionListener for the button
        view.registerComboBoxChange(new CategoryItemChange(model)); // Make a ComboBox ActionListener for the ComboBox
        view.registerButtonSend(new ButtonSend(model, view)); // loob objekti ButtonSend ja paneb meetodile kaasa
                                                            // view .regist... kutsub meetodi
                                                            // ja panemb kaasa ButtonSend obj
    }
}
