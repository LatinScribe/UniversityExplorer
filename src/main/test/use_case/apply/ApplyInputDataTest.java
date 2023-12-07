package use_case.apply;

import org.junit.Test;

public class ApplyInputDataTest {

    @Test
    public void testGetSatScore() {
        ApplyInputData applyInputData = new ApplyInputData("500", "300");
        assert (applyInputData.getSatScore().equals("500"));
    }

    @Test
    public void testgetACTScore() {
        ApplyInputData applyInputData = new ApplyInputData("500", "300");
        assert (applyInputData.getActScore().equals("300"));
    }
}
