package entity;

public interface UsernameValidator {
    public boolean usernameIsValid(String username);

    String minLength();
}
