package controller;

import java.util.Scanner;

public class SalaryListGenerator {

    void getSalaryList(Scanner userInput) {
        System.out.println("Choose month:" + System.lineSeparator() +
                "1: January" + System.lineSeparator() +
                "2: February" + System.lineSeparator() +
                "3: March" + System.lineSeparator() +
                "4: April" + System.lineSeparator() +
                "5: May" + System.lineSeparator() +
                "6: June" + System.lineSeparator() +
                "7: July" + System.lineSeparator() +
                "8: August" + System.lineSeparator() +
                "9: September" + System.lineSeparator() +
                "10: October" + System.lineSeparator() +
                "11: November" + System.lineSeparator() +
                "12: December" + System.lineSeparator());
        String choice = "";
        while (true) {
            choice = userInput.nextLine();
            switch (choice) {
                case "1":

                    break;
                case "2":
                    break;

                case "3":
                case "4":
                case "5":
                case "6":
                case "7":
                case "8":
                case "9":
                case "10":
                case "11":
                case "12":
                    break;
            }
        }
    }
}
