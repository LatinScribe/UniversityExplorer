package entity;

public class UsernameValidatorService implements UsernameValidator{
    @Override
    public boolean usernameIsValid(String username) {
        return username != null && username.length() > 1;
    }
    @Override
    public String getRule() {
        return "Username must be longer than 1 characters (no spaces)";
    }
}
