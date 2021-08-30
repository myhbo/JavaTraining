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

        view.printMessage(View.STARTING_MESSAGE + model.getMinBoundary() + " to " + model.getMaxBoundary());

        model.setCorrectNumber(getRandomNumber(model.getMinBoundary(), model.getMaxBoundary()));

        guessNumber(userInput);
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    private static boolean isDigit(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void guessNumber(Scanner userInput) {
        String input = "0";


        int minLimit = model.getMinBoundary();
        int maxLimit = model.getMaxBoundary();

        while (Integer.parseInt(input) != model.getCorrectNumber()) {

            System.out.println(View.ATTEMPTS + model.getAttempts());
            input = userInput.nextLine();

            try {
                model.attempts.add(Integer.parseInt(input));
                if (Integer.parseInt(input) <= model.getMaxBoundary()
                        && Integer.parseInt(input) >= model.getMinBoundary()) {

                    if (Integer.parseInt(input) != model.getCorrectNumber()) {

                        if (Integer.parseInt(input) < model.getCorrectNumber()) {

                            if (Integer.parseInt(input) > minLimit)
                                minLimit = Integer.parseInt(input);
                            System.out.println(View.HIGHER + "(" + minLimit
                                    + ";" + maxLimit + ")");

                        } else if (Integer.parseInt(input) > model.getCorrectNumber()) {

                            if (Integer.parseInt(input) < maxLimit)
                                maxLimit = Integer.parseInt(input);
                            System.out.println(View.LOWER + "[" + minLimit
                                    + ";" + maxLimit + "]");

                        }
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println(View.WRONG_INPUT);
                input = "0";
            }

        }
        System.out.println(View.YOU_WIN + model.getCorrectNumber());
    }

}
