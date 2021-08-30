public class Model {

    private String hello;
    private String world;
    public final String EXPECTED_FIRST_STRING = "Hello";
    public final String EXPECTED_SECOND_STRING = "world!";


    public void setHelloValue(String input) {
        this.hello = input;
    }

    public void setWorldValue(String input) {
        this.world = input;
    }

    public String getValue() {
        return hello + " " + world;
    }

}
