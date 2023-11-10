package entity;

public class CommonUniversity implements University {

    private final String schoolName;
    private final String state;
    private final String city;
    private final double graduationRate;
    private final double averageTuition;

    public CommonUniversity(String schoolName, String state, String city, double graduationRate, double averageTuition) {
        this.schoolName = schoolName;
        this.state = state;
        this.city = city;
        this.graduationRate = graduationRate;
        this.averageTuition = averageTuition;
    }

    @Override
    public String getSchoolName() {
        return null;
    }

    @Override
    public String getState() {
        return null;
    }

    @Override
    public String getCity() {
        return null;
    }

    @Override
    public double getGraduationRate() {
        return graduationRate;
    }

    @Override
    public double getAverageTuition() {
        return averageTuition;
    }
}
