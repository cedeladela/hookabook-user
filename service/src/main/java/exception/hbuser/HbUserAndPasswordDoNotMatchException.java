package exception.hbuser;

public class HbUserAndPasswordDoNotMatchException extends RuntimeException {

    public HbUserAndPasswordDoNotMatchException() {
        super("Username and password do not match");
    }
}
