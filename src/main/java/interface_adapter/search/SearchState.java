// Author: André

package interface_adapter.search;

import entity.University;

import java.util.Collections;
import java.util.List;

public class SearchState {
    private String searchCriteria = "";
    private String searchError = null;
    private List<University> universities = Collections.emptyList();

    public SearchState(SearchState copy) {
        searchCriteria = copy.searchCriteria;
        searchError = copy.searchError;
        universities = copy.universities;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public SearchState() {
    }

    public String getSearchCriteria() {
        return searchCriteria;
    }

    public String getSearchError() {
        return searchError;
    }

    // Currently used as a test to see if the parameters went through.
    public String getUniversities() {
        return universities.toString();
    }

    public void setSearchCriteria(String searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    public void setSearchError(String searchError) {
        this.searchError = searchError;
    }

    public void setUniversities(List<University> universities) {
        this.universities = universities;
    }

    @Override
    public String toString() {
        return "SignupState{" +
                "username='" + searchCriteria + '\'' +
                ", universities='" + universities + '\'' +
                '}';
        }
    }
