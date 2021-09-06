package controller;

import view.View;
import view.ViewInterface;

import java.util.HashMap;
import java.util.Scanner;

public class NoteTaker {

    private final View view;
    private final Scanner userInput;

    public NoteTaker(View view, Scanner scanner) {
        this.view = view;
        this.userInput = scanner;
    }

    String inputFirstname (Scanner input, String regex){

        String result;

        while (true) {
            view.printMessage(view.getBundle().getString(ViewInterface.INPUT_MESSAGE)
                    + view.getBundle().getString(ViewInterface.FIRSTNAME_MESSAGE));
            if ((result = input.nextLine()).matches(regex))
                return result;
            view.printMessage(view.getBundle().getString(ViewInterface.WRONG_INPUT));

        }
    }

    String inputLastname(Scanner input, String regex) {

        String result;

        while (true) {
            view.printMessage(view.getBundle().getString(ViewInterface.INPUT_MESSAGE)
                    + view.getBundle().getString(ViewInterface.LASTNAME_MESSAGE));
            if ((result = input.nextLine()).matches(regex))
                return result;
            view.printMessage(view.getBundle().getString(ViewInterface.WRONG_INPUT));
        }

    }

    String inputPhonenumber(Scanner input, String regex) {

        String result;

        while (true) {
            view.printMessage(view.getBundle().getString(ViewInterface.INPUT_MESSAGE)
                    + view.getBundle().getString(ViewInterface.PHONE_NUMBER_MESSAGE));
            if ((result = input.nextLine()).matches(regex))
                return result;
            view.printMessage(view.getBundle().getString(ViewInterface.WRONG_INPUT));
        }
    }

    HashMap<String,String> noteToMap () {
        HashMap<String, String> notes = new HashMap<>();
        notes.put(view.getBundle().getString(ViewInterface.FIRSTNAME_FIELD),
                inputFirstname(userInput, view.getBundle().getString(RegexInterface.FIRSTNAME)));
        notes.put(view.getBundle().getString(ViewInterface.LASTNAME_FIELD),
                inputLastname(userInput, view.getBundle().getString(RegexInterface.LASTNAME)));
        notes.put(view.getBundle().getString(ViewInterface.PHONE_NUMBER_FIELD),
                inputPhonenumber(userInput, view.getBundle().getString(RegexInterface.PHONENUMBER)));

        return notes;
    }
}
