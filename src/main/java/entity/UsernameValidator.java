package entity;

public interface UsernameValidator {
    boolean usernameIsValid(String username);

    String getRule();
}
