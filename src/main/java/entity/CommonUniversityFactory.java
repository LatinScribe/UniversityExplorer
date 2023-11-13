package entity;

public class CommonUniversityFactory implements UniversityFactory{

    @Override
    public CommonUniversity create(String schoolName, String state, String city, double graduationRate, double averageTuition) {
        return new CommonUniversity(schoolName, state, city, graduationRate, averageTuition);
    }
}
