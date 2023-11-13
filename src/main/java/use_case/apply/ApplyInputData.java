package use_case.apply;

public class ApplyInputData {
    final private String satScore;
    final private String actScore;

    public ApplyInputData(String satScore, String actScore) {
        this.satScore = satScore;
        this.actScore = actScore;
    }
    String getSatScore(){
        return this.satScore;
    }
    String getActScore(){
        return actScore;
    }
}
