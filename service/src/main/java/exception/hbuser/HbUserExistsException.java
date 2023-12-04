package exception.hbuser;

public class HbUserExistsException extends RuntimeException {

    public HbUserExistsException(boolean usernameExists, boolean emailExists) {
        super(buildErrorMessage(usernameExists, emailExists));
    }

    private static String buildErrorMessage(boolean usernameExists, boolean emailExists) {
        if (usernameExists && emailExists) {
            return "User with same username and email already exists";
        } else if (usernameExists) {
            return "User with same username already exists";
        } else if (emailExists) {
            return "User with same email already exists";
        } else {
            return "User already exists";
        }
    }
}
