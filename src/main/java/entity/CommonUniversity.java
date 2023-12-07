//Author: André
package entity;

/**
 * An entity that stores all of the attributes of a given institution, including its name, state, admissionrate, etc.
 * @author Andre
 */
public class CommonUniversity implements University {

    private final Integer schoolID;
    private final String schoolName;
    private final String state;
    private final String city;
    private final Double admissionRate;
    private final Integer averageInStateTuition;
    private final Integer averageOutOfStateTuition;
    private final Integer averageSATScore;
    private final Integer averageACTScore;
    private final String url;

    /**
     * Instantiates a CommonUniversity entity.
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
     */
    public CommonUniversity(Integer schoolID, String schoolName, String state, String city, Double admissionRate, Integer averageInStateTuition, Integer averageOutOfStateTuition, Integer averageSATScore, Integer averageACTScore, String url) {
        this.schoolID = schoolID;
        this.schoolName = schoolName;
        this.state = state;
        this.city = city;
        this.admissionRate = admissionRate;
        this.averageInStateTuition = averageInStateTuition;
        this.averageOutOfStateTuition = averageOutOfStateTuition;
        this.averageSATScore = averageSATScore;
        this.averageACTScore = averageACTScore;
        this.url = url;
    }

    /**
     * Returns the schoolID of a CommonUniversity.
     * @return schoolID
     */
    @Override
    public Integer getSchoolID() {
        return schoolID;
    }

    /**
     * Returns the schoolName of a CommonUniversity.
     * @return schoolName
     */
    @Override
    public String getSchoolName() {
        return schoolName;
    }

    /**
     * Returns the state of a CommonUniversity.
     * @return state
     */
    @Override
    public String getState() {
        return state;
    }

    /**
     * Returns the city of a CommonUniversity.
     * @return city
     */
    @Override
    public String getCity() {
        return city;
    }

    /**
     * Returns the admissionRate of a CommonUniversity.
     * @return admissionRate
     */
    @Override
    public Double getAdmissionRate() {
        return admissionRate;
    }

    /**
     * Returns the averageInStateTuition of a CommonUniversity.
     * @return averageInStateTuition
     */
    @Override
    public Integer getAverageInStateTuition() {
        return averageInStateTuition;
    }

    /**
     * Returns the averageOutOfStateTuition of a CommonUniversity.
     * @return averageOutOfStateTuition
     */
    @Override
    public Integer getAverageOutOfStateTuition() {
        return averageOutOfStateTuition;
    }

    /**
     * Returns the averageSATScore of a CommonUniversity.
     * @return averageSATScore
     */
    @Override
    public Integer getAverageSATScore() {
        return averageSATScore;
    }

    /**
     * Returns the averageACTScore of a CommonUniversity.
     * @return averageACTScore
     */
    @Override
    public Integer getAverageACTScore() {
        return averageACTScore;
    }

    /**
     * Returns the url of a CommonUniversity.
     * @return url
     */
    @Override
    public String getUrl() {
        return url;
    }

    /**
     * Returns a formatted string of the CommonUniversity entity, containing all of its attributes. If an attribute
     * is null, it will be displayed as N/A.
     * @return A formatted string of a given university.
     */
    @Override
    public String toString() {
        Object[] objects = toStringHelper();
        for (int i = 0; i < objects.length; i++) {
            if (objects[i] == null) {
                objects[i] = "N/A";
            }
        }
        String start = "Institution Name: " + objects[0] + "\n" +
                "Location: " + objects[1] + ", " + objects[2] + "\n" +
                "Admission Rate: " + objects[3] + "\n";
        String middle1 = null;
        String middle2 = null;
        if (!objects[4].equals("N/A")) {
            middle1 = "Average Tuition (In State): $" + objects[4] + "\n";
        } else {
            middle1 = "Average Tuition (In State): " + objects[4] + "\n";
        }
        if (!objects[5].equals("N/A")) {
            middle2 = "Average Tuition (Out of State): $" + objects[5] + "\n";
        } else {
            middle2 = "Average Tuition (Out of State): " + objects[5] + "\n";
        }
        String end = "Average SAT Score: " + objects[6] + "\n" +
                "Average ACT Score: " + objects[7] + "\n" +
                "URL: " + objects[8];

        return start + middle1 + middle2 + end;
    }

    /**
     * A helper method in which all the attributes (excluding schoolID) are placed into an object array.
     * @return An array of objects
     */
    private Object[] toStringHelper() {
        return new Object[]{schoolName, city, state, admissionRate, averageInStateTuition, averageOutOfStateTuition,
                averageSATScore, averageACTScore, url};
    }
}
