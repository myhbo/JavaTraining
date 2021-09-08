package controller;
import model.Model;
import view.View;
import view.ViewInterface;

import java.util.HashMap;
import java.util.Scanner;

public class Controller {

    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void processUser() {
        /**
         * DB imitation
         */
        HashMap<String,String> firstRecord;

        {
            firstRecord = new HashMap<>();
            firstRecord.put(View.bundle.getString(ViewInterface.LOGIN_FIELD), "ivan1234");
            firstRecord.put(View.bundle.getString(ViewInterface.FIRSTNAME_FIELD), "Ivan");
            firstRecord.put(View.bundle.getString(ViewInterface.LASTNAME_FIELD), "Ivanov");
            firstRecord.put(View.bundle.getString(ViewInterface.PHONE_NUMBER_FIELD), "+380504444444");
        }

        HashMap<String,String> secondRecord;

        {
            secondRecord = new HashMap<>();
            secondRecord.put(View.bundle.getString(ViewInterface.LOGIN_FIELD), "petr1234");
            secondRecord.put(View.bundle.getString(ViewInterface.FIRSTNAME_FIELD), "Petro");
            secondRecord.put(View.bundle.getString(ViewInterface.LASTNAME_FIELD), "Petrov");
            secondRecord.put(View.bundle.getString(ViewInterface.PHONE_NUMBER_FIELD), "+380663333333");
        }
        Scanner userInput = new Scanner(System.in);
        try {
            model.addNotes(firstRecord);
            model.addNotes(secondRecord);
        } catch(LoginAlreadyTakenException e) {

        }
        while (true) {
            try {

                if(model.addNotes(new NoteTaker(view, userInput).noteToMap()))
                    break;
            } catch (LoginAlreadyTakenException e) {
                view.printException(e);
            }
        }
        System.out.println(model.getNotes().toString());
    }
}




