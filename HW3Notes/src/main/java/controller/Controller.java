package controller;
import model.Model;
import view.View;
import view.ViewInterface;

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

        Model note = new Model(inputFirstname(userInput, view.getBundle().getString(RegexInterface.FIRSTNAME)),
                inputLastname(userInput, view.getBundle().getString(RegexInterface.LASTNAME)),
                inputPhonenumber(userInput, view.getBundle().getString(RegexInterface.PHONENUMBER)));
    }

    private String inputFirstname (Scanner input, String regex){
        String result;
        view.printMessage(view.getBundle().getString(ViewInterface.INPUT_MESSAGE)
                + view.getBundle().getString(ViewInterface.FIRSTNAME_MESSAGE));

        while (!(input.hasNext() && (result = input.next()).matches(regex))) {

            view.printMessage(ViewInterface.WRONG_INPUT);

        }
        return result;
    }

    private String inputLastname(Scanner input, String regex) {
        String result;

        view.printMessage(view.getBundle().getString(ViewInterface.INPUT_MESSAGE)
                + view.getBundle().getString(ViewInterface.LASTNAME_MESSAGE));

        while (!(input.hasNext() && (result = input.next()).matches(regex))) {

            view.printMessage(ViewInterface.WRONG_INPUT);

        }
        return result;
    }

    private long inputPhonenumber(Scanner input, String regex) {
        String result;

        view.printMessage(view.getBundle().getString(ViewInterface.INPUT_MESSAGE)
                + view.getBundle().getString(ViewInterface.PHONE_NUMBER_MESSAGE));

        while (!(input.hasNext() && (result = input.next()).matches(regex))) {

            view.printMessage(view.getBundle().getString(ViewInterface.WRONG_INPUT));

        }
        return Long.parseLong(result);
    }

}
