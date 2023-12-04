// Author: André

package interface_adapter.results;

import entity.University;
import interface_adapter.search.SearchState;

import javax.xml.transform.Result;
import java.util.Collections;
import java.util.List;

public class ResultsState {
    private List<String> universities = Collections.emptyList();
    private String chosenUniversityString = null;
    private String searchError = null;
    public String prevView = "";

    public ResultsState(ResultsState copy) {
        universities = copy.universities;
        chosenUniversityString = copy.chosenUniversityString;
        searchError = copy.searchError;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public ResultsState() {
    }

    public List<String> getUniversityNames() {
        return universities;
    }

    public String getChosenUniversityString() {return chosenUniversityString;}

    public String getSearchError() {return searchError;}
    public void setPreviousView(String prevView) {this.prevView = prevView;}

    public void setUniversityNames(List<String> universities) {
        this.universities = universities;
    }

    public void setChosenUniversityString(String string) {this.chosenUniversityString = string;}

    public void setSearchError(String searchError) {this.searchError = searchError;}

    @Override
    public String toString() {
        return "ResultsState{ " +
                "Universities='" + universities + '\'' +
                " }";
    }
}
