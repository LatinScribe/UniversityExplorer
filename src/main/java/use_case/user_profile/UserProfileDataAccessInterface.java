package use_case.user_profile;

public interface UserProfileDataAccessInterface {

    boolean hasProfile(String username);

    void save(UserProfileInputData userdata);


}
