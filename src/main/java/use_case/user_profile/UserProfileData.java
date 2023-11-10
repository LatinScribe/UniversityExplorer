package use_case.user_profile;

public class UserProfileData {

    final private String username;

    final private String name;

    final private String nationality;

    final private String location;

    final private String preferredUni;

    public UserProfileData(String user, String name, String nationality, String location, String preferredUni) {
        this.username = user;
        this.name = name;
        this.nationality = nationality;
        this.location = location;
        this.preferredUni = preferredUni;
    }

    String getUsername() {
        return this.username;
    }

    String getName() {
        return this.name;
    }

    String getNationality() {
        return this.nationality;
    }

    String getLocation() {
        return this.location;
    }

    String getPreferredUni() {
        return this.preferredUni;
    }

}
