package entity;

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

    public CommonUniversity(Integer schoolID, String schoolName, String state, String city, Double admissionRate, Integer averageInStateTuition, int averageOutOfStateTuition, Double averageSATScore, Double averageACTScore, String url) {
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
        return "University Name: " + schoolName + "\"" +
                "Location: " + city + ", " + state + "\"" +
                "Admission Rate: " + admissionRate.toString() + "\"" +
                "Average Tuition (In State): " + averageInStateTuition.toString() + "\"" +
                "Average Tuition (Out of State) " + averageOutOfStateTuition.toString() + "\"" +
                "Average SAT Score: " + averageSATScore.toString() + "\"" +
                "Average ACT Score: " + averageACTScore.toString() + "\"" +
                "URL: " + url;
    }
}
