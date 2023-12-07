package entity;

/**
 * A factory used to instantiate instances of Universities throughout the program.
 * @author Andre
 */
public class CommonUniversityFactory implements UniversityFactory {

    /**
     * Returns a created CommonUniversity object.
     * @param schoolID
     * @param schoolName
     * @param state
     * @param city
     * @param admissionRate
     * @param averageInStateTuition
     * @param averageOutOfStateTuition
     * @param averageSATScore
     * @param averageACTScore
     * @param url
     * @return A CommonUniversity object that can be used in various parts of the program.
     */
    @Override
    public CommonUniversity create(Integer schoolID, String schoolName, String state, String city, Double admissionRate, Integer averageInStateTuition, Integer averageOutOfStateTuition, Integer averageSATScore, Integer averageACTScore, String url) {
        return new CommonUniversity(schoolID, schoolName, state, city, admissionRate, averageInStateTuition, averageOutOfStateTuition, averageSATScore, averageACTScore, url);
    }
}
