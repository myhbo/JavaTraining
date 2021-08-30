public class View {

    public static final String STARTING_MESSAGE = "Hi! Lets start the game. Guess random number in range from ";
    public static final String WRONG_INPUT = "Wrong input. Use integer numbers in your range";
    public static final String YOU_WIN = "You found the number! It was ";
    public static final String HIGHER = "Your number is higher. Try this range ";
    public static final String LOWER = "Your number is lower. Try this range ";
    public static final String ATTEMPTS = "ATTEMPTS: ";


    public void printMessage(String message) {
        System.out.println(message);
    }

}
