package controller;

public class LoginAlreadyTakenException extends Throwable{

    private final String takenLogin;

    public LoginAlreadyTakenException(String message, String login) {
        super(message);
        this.takenLogin = login;
    }
    public String getTakenLogin() {
        return takenLogin;
    }
}
