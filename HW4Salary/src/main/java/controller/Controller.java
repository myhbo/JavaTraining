package controller;

import model.db.Model;
import view.View;

import java.util.Scanner;

public class Controller {

    private final Model model;
    private final View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void processUser() {
        SalaryListGenerator salaryListGenerator = new SalaryListGenerator();
        Scanner userInput = new Scanner(System.in);
        String choice = "";
        System.out.println("Choose option:" + System.lineSeparator() +
                "1: Salary report by month " + System.lineSeparator() +
                "2: Set salary fond" + System.lineSeparator() +
                "3: Change salary calculation" + System.lineSeparator() +
                "4: Add employee" + System.lineSeparator() +
                "5: Fire employee" + System.lineSeparator() +
                "6: Change employee position" + System.lineSeparator() +
                "7: Add worker to manager" + System.lineSeparator() +
                "e: Exit" + System.lineSeparator());

        while(!choice.equals("e")) {
            choice = userInput.nextLine();
            switch (choice) {
                case "1": salaryListGenerator.getSalaryList(userInput);
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5":
                    break;
                case "6":
                    break;
                case "7":
                    break;
                case "e":
                    break;
            }
        }
    }
}
