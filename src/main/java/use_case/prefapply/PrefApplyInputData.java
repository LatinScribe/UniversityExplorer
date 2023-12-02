package use_case.prefapply;

public class PrefApplyInputData {
    final private String satScore;
    final private String actScore;

    public PrefApplyInputData(String satScore, String actScore) {
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
