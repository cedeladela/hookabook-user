package exception.hbuser;

public class HbUserNotFoundException extends RuntimeException {

    public HbUserNotFoundException(Long userId) {
        super("Comment with ID " + userId + " not found");
    }
}
