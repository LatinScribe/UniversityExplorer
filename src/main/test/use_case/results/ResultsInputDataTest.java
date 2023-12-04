package use_case.results;

import org.junit.jupiter.api.Test;

public class ResultsInputDataTest {

    @Test
    void testGetUniName() {
        ResultsInputData resultsInputData = new ResultsInputData("boogaloo");
        assert (resultsInputData.getUniversityName().equals("boogaloo"));
    }
}
