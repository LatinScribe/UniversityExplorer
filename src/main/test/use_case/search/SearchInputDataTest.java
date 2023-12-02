package use_case.search;

import org.junit.Test;

public class SearchInputDataTest {

    @Test
    public void testGetSearchCriteria() {
        SearchInputData searchInputData = new SearchInputData("New york");
        assert (searchInputData.getSearchCriteria().equals("New york"));
    }

}
