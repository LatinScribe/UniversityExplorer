package entity;

public interface PasswordValidator {
    boolean passwordIsValid(String password);

    String getRule();
}
