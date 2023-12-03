package entity;

/**
 * Interface for the function of checking whether the given password is valid
 *
 * @author Henry
 */
public interface PasswordValidator {
    boolean passwordIsValid(String password);

    /**
     * Method for getting the rule used by this validator to check passwords
     *
     * @return the rule used by the validator to check usernames in string form
     */
    String getRule();
}
