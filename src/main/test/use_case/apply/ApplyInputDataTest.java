package use_case.apply;

import org.junit.Test;

public class ApplyInputDataTest {

    @Test
    public void testGetSatScore() {
        ApplyInputData applyInputData = new ApplyInputData("500.0", "300.0");
        assert (applyInputData.getSatScore().equals("500.0"));
    }

    @Test
    public void testgetACTScore() {
        ApplyInputData applyInputData = new ApplyInputData("500.0", "300.0");
        assert (applyInputData.getActScore().equals("300.0"));
    }
}
