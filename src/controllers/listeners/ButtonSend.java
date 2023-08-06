package controllers.listeners;

import models.Model;
import views.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonSend implements ActionListener {

    private Model model;
    private View view;

    public ButtonSend(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void actionPerformed(ActionEvent nuppSendEvent) {
        System.out.println("sitt lugu");
    }
}
