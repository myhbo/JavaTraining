package controller;

import java.util.Date;
import java.util.Scanner;

public class SalaryListGenerator {
    long calculateSalariesByMonth(Date date) {


    }

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
                    break;
                case "4":
                    break;
                case "5":
                    break;
                case "6":
                    break;
                case "7":
                    break;
                case "8":
                    break;
                case "9":
                    break;
                case "10":
                    break;
                case "11":
                    break;
                case "12":
                    break;
            }
        }
    }
}
