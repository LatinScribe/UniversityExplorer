package use_case.zip_search;

/**
 * A class that returns the ZipSearchCriteria strings (inputs by the user) into a ZipSearchInputData object.
 * @author Diego
 */
public class ZipSearchInputData {
    final private String zipSearch;
    final private String radSearch;

    /**
     * Instantiates a ZipSearchInputData
     * @param zipSearch
     * @param radSearch
     */
    public ZipSearchInputData(String zipSearch, String radSearch) {
        this.zipSearch = zipSearch;
        this.radSearch = radSearch;
    }

    /**
     * Returns the zipcode data from the zip search attribute
     * @return String
     */
    String getZipSearch() {
        return zipSearch;
    }

    /**
     * Returns the distance data from the radius attribute
     * @return String
     */
    String getRadSearch() {
        return radSearch;
    }
}
