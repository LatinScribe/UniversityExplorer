package entity;

public class CommonUniversity implements University {

    private final String schoolName;
    private final String state;
    private final String city;
    private final Double admissionRate;
    private final Integer averageInStateTuition;
    private final Integer averageOutOfStateTuition;

    public CommonUniversity(String schoolName, String state, String city, Double admissionRate, Integer averageInStateTuition, Integer averageOutOfStateTuition) {
        this.schoolName = schoolName;
        this.state = state;
        this.city = city;
        this.admissionRate = admissionRate;
        this.averageInStateTuition = averageInStateTuition;
        this.averageOutOfStateTuition = averageOutOfStateTuition;
    }

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

    public Integer getAverageOutOfStateTuition() {
        return averageOutOfStateTuition;
    }
}
