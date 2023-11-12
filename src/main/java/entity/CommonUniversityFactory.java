package entity;


public class CommonUniversityFactory implements UniversityFactory {

    @Override
    public CommonUniversity create(String schoolName, String state, String city, Double admissionRate, Integer averageInStateTuition, Integer averageOutOfStateTuition) {
        return new CommonUniversity(schoolName, state, city, admissionRate, averageInStateTuition, averageOutOfStateTuition);
    }
}
