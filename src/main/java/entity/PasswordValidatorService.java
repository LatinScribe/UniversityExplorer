package entity;

public class PasswordValidatorService implements PasswordValidator {
    public boolean passwordIsValid(String password) {
        return password != null && password.length() > 5;
    }

    @Override
    public String getRule() {
        return "Password must be longer than 5 characters (no spaces)";
    }
}
