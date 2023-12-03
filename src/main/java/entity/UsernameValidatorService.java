package entity;

/**
 * Class for checking whether the given username is valid
 * Checks whether the given username has at least one character
 *
 * @author Henry
 */
public class UsernameValidatorService implements UsernameValidator {
    /**
     * method that checks and returns whether the username has at least one character
     *
     * @param username given username
     * @return boolean representing whether this is a valid username
     */
    @Override
    public boolean usernameIsValid(String username) {
        return username != null && username.length() > 1;
    }

    /**
     * Method for getting the rule used by this validator to check usernames
     *
     * @return the rule used by the validator to check usernames in string form
     */
    @Override
    public String getRule() {
        return "Username must be longer than 1 characters (no spaces)";
    }
}
