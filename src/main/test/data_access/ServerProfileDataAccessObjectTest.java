package data_access;

import entity.UserPreferences;
import entity.UserPreferencesFactory;
import entity.UserProfile;
import org.jetbrains.annotations.Nullable;
import org.junit.internal.runners.statements.Fail;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.Assert.fail;

class ServerProfileDataAccessObjectTest {

    FileTokenDataAccessObject fileTokenDataAccessObject = new FileTokenDataAccessObject();
    UserPreferencesFactory userPreferencesFactory = new UserPreferencesFactory();
    ServerProfileDataAccessObject db = new ServerProfileDataAccessObject(fileTokenDataAccessObject, userPreferencesFactory);
    @Test
    void saveProfile() {
        fileTokenDataAccessObject.save_token(19,"KKo1mOQFjzTmowL4BtMzWZ2ZsIQf8p7f");
        UserPreferences userPreferences = new UserPreferences(20000, "commerce", 100000, new int[]{1,2}, "Boston");
        try {
            db.saveProfile(userPreferences);
        } catch (Exception e) {
            assert (e.getMessage().equals("This user already has a profile in the system. If you want to moddify the profile, please use updateProfile instead"));
        }
    }

    @Test
    void updateProfile() {
        fileTokenDataAccessObject.save_token(19,"KKo1mOQFjzTmowL4BtMzWZ2ZsIQf8p7f");
        UserPreferences userPreferences1 = new UserPreferences(100000, "compsci", 100000, new int[]{1,2}, "New York");
        UserProfile userProfile;
        try {
            db.updateProfile(userPreferences1);
            userProfile = db.getProfile();
        }catch (Exception e) {
            Assertions.fail("something went wrong");
            return;
        }
        assert userProfile != null;
        assert (userProfile.getFinAidRequirement().equals(100000));
        assert (userProfile.getPreferredProgram().equals("compsci"));
        assert (userProfile.getAvgSalary().equals(100000));
        assert (userProfile.getUniversityRankingRange()[0] == 1);
        assert (userProfile.getUniversityRankingRange()[1] == 2);
        assert (userProfile.getLocationPreference().equals("New York"));
    }

    @Test
    void getProfile() {
        fileTokenDataAccessObject.save_token(19,"KKo1mOQFjzTmowL4BtMzWZ2ZsIQf8p7f");
        UserPreferences userPreferences1 = new UserPreferences(100000, "compsci", 100000, new int[]{1,2}, "New York");
        UserProfile userProfile;
        try {
            db.updateProfile(userPreferences1);
            userProfile = db.getProfile();
        }catch (Exception e) {
            Assertions.fail("something went wrong");
            return;
        }
        assert userProfile != null;
        assert (userProfile.getFinAidRequirement().equals(100000));
        assert (userProfile.getPreferredProgram().equals("compsci"));
        assert (userProfile.getAvgSalary().equals(100000));
        assert (userProfile.getUniversityRankingRange()[0] == 1);
        assert (userProfile.getUniversityRankingRange()[1] == 2);
        assert (userProfile.getLocationPreference().equals("New York"));
    }

}