package use_case.results;

import entity.CommonUniversity;
import entity.University;
import org.junit.Test;

public class ResultsOutputDataTest {

    @Test
    public void testGetUniversityName() {
        University university = new CommonUniversity(1, "tester", "NY", "New York", 50.3, 5000, 10000, 750, 250, "test.com");
        ResultsOutputData resultsOutputData = new ResultsOutputData(university);
        assert (resultsOutputData.getUniversity().equals(university));
    }
}
