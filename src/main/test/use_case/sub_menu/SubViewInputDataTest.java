package use_case.sub_menu;

import org.junit.Test;

public class SubViewInputDataTest {

    @Test
    public void testGetNext_panel() {
        SubViewInputData subViewInputData = new SubViewInputData("search");
        assert (subViewInputData.getNext_panel().equals("search"));

        SubViewInputData subViewInputData1 = new SubViewInputData("recommendation");
        assert (subViewInputData1.getNext_panel().equals("recommendation"));

        SubViewInputData subViewInputData2 = new SubViewInputData("zip_search");
        assert (subViewInputData2.getNext_panel().equals("zip_search"));
    }

}
