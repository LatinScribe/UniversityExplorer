package data_access;

import entity.UserPreferences;

import java.io.IOException;

public interface ProfileDataAccessInterface {
    String saveProfile(int finAidReq, String prefProg, int avgSalary, int uniRankingRange, String locationPref) throws IOException;

    String updateProfile(int finAidReq, String prefProg, int avgSalary, int uniRankingRange, String locationPref) throws IOException;

    UserPreferences getProfile() throws IOException;
}
