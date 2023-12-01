// Author: Diego
package use_case.zip_search;

import entity.University;

import java.util.List;

public class ZipSearchOutputData {
    private List<University> universities;

    public ZipSearchOutputData(List<University> universities) {
        this.universities = universities;
    }

    public List<University> getUniversities() {
        return universities;
    }
}
