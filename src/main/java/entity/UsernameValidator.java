package entity;

/**
 * Interface for the function of checking whether the given username is valid
 *
 * @author Henry
 */
public interface UsernameValidator {
    /**
     * method that checks and returns whether the username satisfies the rules
     *
     * @param username given username
     * @return boolean representing whether this is a valid username
     */
    boolean usernameIsValid(String username);

    /**
     * Method for getting the rule used by this validator to check usernames
     *
     * @return the rule used by the validator to check usernames in string form
     */
    String getRule();
}
