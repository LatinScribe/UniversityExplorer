package entity;

public interface UniversityFactory {
    CommonUniversity create(Integer schoolID, String schoolName, String state, String city, Double admissionRate, Integer averageInStateTuition, Integer averageOutOfStateTuition, Double averageSATScore, Double averageACTScore, String url);

}
