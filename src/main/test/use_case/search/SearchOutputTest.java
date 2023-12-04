package use_case.search;

import entity.CommonUniversity;
import entity.University;
import org.junit.Test;
import use_case.apply.ApplyOutputData;

import java.util.ArrayList;
import java.util.List;

public class SearchOutputTest {
    @Test
    public void testGetUniversity() {
        University university = new CommonUniversity(1, "tester", "NY", "New York", 50.3, 5000, 10000, 750, 250, "test.com");
        List<University> universities = new ArrayList<>();
        universities.add(university);
        SearchOutputData searchOutputData = new SearchOutputData(universities);
        assert (searchOutputData.getUniversities().equals(universities));
    }
}
