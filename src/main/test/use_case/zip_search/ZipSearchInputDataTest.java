package use_case.zip_search;

import org.junit.Test;

public class ZipSearchInputDataTest {

    @Test
    public void testGetZipSearch() {
        ZipSearchInputData zipSearchInputData = new ZipSearchInputData("02139", "1mi");
        assert (zipSearchInputData.getZipSearch().equals("02139"));
    }

    @Test
    public void testGetRadSearch() {
        ZipSearchInputData zipSearchInputData = new ZipSearchInputData("02139", "1mi");
        assert (zipSearchInputData.getRadSearch().equals("1mi"));
    }
}
