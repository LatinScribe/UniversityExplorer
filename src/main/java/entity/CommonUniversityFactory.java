package entity;


public class CommonUniversityFactory implements UniversityFactory {

    @Override
    public CommonUniversity create(Integer schoolID, String schoolName, String state, String city, Double admissionRate, Integer averageInStateTuition, Integer averageOutOfStateTuition, Double averageSATScore, Double averageACTScore, String url) {
        return new CommonUniversity(schoolID, schoolName, state, city, admissionRate, averageInStateTuition, averageOutOfStateTuition, averageSATScore, averageACTScore, url);
    }
}
