package entity;

public class UsernameValidatorService implements UsernameValidator{
    @Override
    public boolean usernameIsValid(String username) {
        return username != null && username.length() > 1;
    }
    @Override
    public String minLength() {
        return "2";
    }
}
