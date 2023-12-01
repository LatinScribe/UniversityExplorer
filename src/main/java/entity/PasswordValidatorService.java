package entity;

public class PasswordValidatorService implements PasswordValidator {
    public boolean passwordIsValid(String password) {
        return password != null && password.length() > 5;
    }
    @Override
    public String minLength() {
        return "6";
    }
}
