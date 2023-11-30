// Author: André

package interface_adapter.results;

import entity.University;
import interface_adapter.search.SearchState;

import javax.xml.transform.Result;
import java.util.Collections;
import java.util.List;

public class ResultsState {
    private List<University> universities = Collections.emptyList();
    private University chosenUniversity = null;
    private String searchError = null;

    public ResultsState(ResultsState copy) {
        universities = copy.universities;
        chosenUniversity = copy.chosenUniversity;
        searchError = copy.searchError;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public ResultsState() {
    }

    public List<University> getUniversities() {
        return universities;
    }

    public University getChosenUniversity() {return chosenUniversity;}

    public String getSearchError() {return searchError;}

    public void setUniversities(List<University> universities) {
        this.universities = universities;
    }

    public void setChosenUniversity(University university) {this.chosenUniversity = university;}

    public void setSearchError(String searchError) {this.searchError = searchError;}

    @Override
    public String toString() {
        return "ResultsState{ " +
                "Universities='" + universities + '\'' +
                " }";
    }
}
