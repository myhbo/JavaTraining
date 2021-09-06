package controller;
import model.Model;
import view.View;

import java.util.Scanner;

public class Controller {

    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void processUser() {
        Scanner userInput = new Scanner(System.in);

        model.addNotes(new NoteTaker(view, userInput).noteToMap());
        System.out.println(model.getNotes().toString());
    }
}




