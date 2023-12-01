// Author: Diego
package use_case.zip_search;

public class ZipSearchInputData {
    final private String zipSearch;
    final private String radSearch;
    public ZipSearchInputData(String zipSearch, String radSearch) {
        this.zipSearch = zipSearch;
        this.radSearch = radSearch;
    }

    String getZipSearch() {
        return zipSearch;
    }

    String getRadSearch() {
        return radSearch;
    }
}
