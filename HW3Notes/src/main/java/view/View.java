package view;

import java.util.Locale;
import java.util.ResourceBundle;

public class View {

    static String RESOURCE_BUNDLE = "messages";
    public static Locale local = new Locale("ua");
                        //new Locale("en");
    public static ResourceBundle bundle =
            ResourceBundle.getBundle(RESOURCE_BUNDLE, local);

    public ResourceBundle getBundle() {
        return bundle;
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

}
