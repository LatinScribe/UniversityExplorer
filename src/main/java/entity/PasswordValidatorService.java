package entity;

/**
 * Class for checking whether the given password is valid
 * Checks whether the given username has at least 6 characters
 *
 * @author Henry
 */
public class PasswordValidatorService implements PasswordValidator {
    /**
     * Checks whether the given password is valid,
     * .i.e whether it has at least 6 characters
     *
     * @param password given password to be checked
     * @return boolean representing whether the password has at least 6 characters
     */
    public boolean passwordIsValid(String password) {
        return password != null && password.length() > 5;
    }

    /**
     * Method for getting the rule used by this validator to check passwords
     *
     * @return the rule used by the validator to check usernames in string form
     */
    @Override
    public String getRule() {
        return "Password must be longer than 5 characters (no spaces)";
    }
}
