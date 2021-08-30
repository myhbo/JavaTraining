import java.util.Scanner;

public class Controller {

    private Model model;
    private View view;

    public Controller(Model model, View view) {

        this.model = model;
        this.view = view;
    }

    public void processUser() {
        Scanner sc = new Scanner(System.in);

        model.setHelloValue(inputHelloValueWithScanner(sc));
        model.setWorldValue(inputWorldValueWithScanner(sc));

        view.printMessage(model.getValue());
    }

    public String inputHelloValueWithScanner(Scanner sc) {
        view.printMessage(View.INPUT_STRING_DATA);
        while (!sc.nextLine().equals(model.EXPECTED_FIRST_STRING)) {
            view.printMessage(View.WRONG_INPUT + View.INPUT_STRING_DATA);
        }
        return "Hello";
    }

    public String inputWorldValueWithScanner(Scanner sc) {
        view.printMessage(View.INPUT_NEXT_STRING_DATA);
        while (!sc.nextLine().equals(model.EXPECTED_SECOND_STRING)) {
            view.printMessage(View.WRONG_INPUT + View.INPUT_NEXT_STRING_DATA);
        }
        return "world!";
    }

}
