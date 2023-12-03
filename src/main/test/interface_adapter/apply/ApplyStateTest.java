package interface_adapter.apply;

import entity.CommonUniversity;
import entity.University;
import org.junit.Test;

public class ApplyStateTest {

    ApplyState applyState = new ApplyState();

    @Test
    public void testGetACT() {
        applyState.setAct("28.0");
        assert (applyState.getAct().equals("28.0"));
    }

    @Test
    public void testGetSAT() {
        applyState.setSat("1200.0");
        assert (applyState.getSat().equals("1200.0"));
    }

    @Test
    public void testGetActError() {
        applyState.setActError("Test");
        assert (applyState.getActError().equals("Test"));
    }

    @Test
    public void testGetSatError() {
        applyState.setSatError("Test");
        assert (applyState.getSatError().equals("Test"));
    }

    @Test
    public void testGetSubmitStatus() {
        applyState.setSubmitStatus("Test");
        assert (applyState.getSubmitStatus().equals("Test"));
    }

    @Test
    public void testGetUni() {
        University university = new CommonUniversity(1, "tester", "NY", "New York", 50.3, 5000, 10000, 750.0, 250.0, "test.com");
        applyState.setUni(university);
        assert (applyState.getUni().equals(university));
    }

    @Test
    public void testGetUniversityError() {
        applyState.setUniversityError("test");
        assert (applyState.getUniversityError().equals("test"));
    }

    @Test
    public void testToString() {
        applyState.setAct("28.0");
        applyState.setSat("1200.0");
        assert applyState.toString().equals("ApplyState{act='28.0\', sat=1200.0}");
    }
}
