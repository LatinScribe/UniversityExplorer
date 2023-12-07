package use_case.apply;

import entity.CommonUniversity;
import entity.University;
import org.junit.Test;

public class ApplyOutputDataTest {

    @Test
    public void testGetUniversity() {
        University university = new CommonUniversity(1, "tester", "NY", "New York", 50.3, 5000, 10000, 750, 250, "test.com");
        ApplyOutputData applyOutputData = new ApplyOutputData(university);
        assert (applyOutputData.getUniversity().equals(university));
    }
}
