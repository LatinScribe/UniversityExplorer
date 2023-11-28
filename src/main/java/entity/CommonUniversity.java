package entity;

import java.sql.Array;

public class CommonUniversity implements University {

    private final Integer schoolID;
    private final String schoolName;
    private final String state;
    private final String city;
    private final Double admissionRate;
    private final Integer averageInStateTuition;
    private final Integer averageOutOfStateTuition;
    private final Double averageSATScore;
    private final Double averageACTScore;
    private final String url;

    public CommonUniversity(Integer schoolID, String schoolName, String state, String city, Double admissionRate, Integer averageInStateTuition, Integer averageOutOfStateTuition, Double averageSATScore, Double averageACTScore, String url) {
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

    @Override
    public Integer getSchoolID() {return schoolID;}

    @Override
    public String getSchoolName() {
        return schoolName;
    }

    @Override
    public String getState() {
        return state;
    }

    @Override
    public String getCity() {
        return city;
    }

    @Override
    public Double getAdmissionRate() {
        return admissionRate;
    }

    @Override
    public Integer getAverageInStateTuition() {
        return averageInStateTuition;
    }

    @Override
    public Integer getAverageOutOfStateTuition() {
        return averageOutOfStateTuition;
    }

    @Override
    public Double getAverageSATScore() {
        return averageSATScore;
    }

    @Override
    public Double getAverageACTScore() {
        return averageACTScore;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        Object[] objects = toStringHelper();
        for (int i = 0; i < objects.length; i++) {
            if (objects[i] == null) {
                objects[i] = new String("N/A");
            }
        }
        return "Institution Name: " + objects[0] + "\n" +
                "Location: " + objects[1] + ", " + objects[2] + "\n" +
                "Admission Rate: " + objects[3] + "\n" +
                "Average Tuition (In State): " + objects[4] + "\n" +
                "Average Tuition (Out of State) " + objects[5] + "\n" +
                "Average SAT Score: " + objects[6] + "\n" +
                "Average ACT Score: " + objects[7] + "\n" +
                "URL: " + objects[8];
    }

    private Object[] toStringHelper() {
        return new Object[] {schoolName, city, state, admissionRate, averageInStateTuition, averageOutOfStateTuition, averageSATScore, averageACTScore, url};

    }
}
