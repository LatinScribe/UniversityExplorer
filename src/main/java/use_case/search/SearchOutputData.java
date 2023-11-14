// Author: André

package use_case.search;

import entity.University;
import java.util.List;

public class SearchOutputData {

    private List<University> universities;

    public SearchOutputData(List<University> universities) {
        this.universities = universities;
    }

    public List<University> getUniversities() {
        return universities;
    }

}
