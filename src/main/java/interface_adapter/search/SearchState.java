// Author: André

package interface_adapter.search;

import entity.University;

import java.util.Collections;
import java.util.List;

public class SearchState {
    private String searchCriteria = "";
    private String searchError = null;
    private String prevView = "";

    public SearchState(SearchState copy) {
        searchCriteria = copy.searchCriteria;
        searchError = copy.searchError;
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

    public String getPrevView() {return prevView;}

    public void setSearchCriteria(String searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    public void setSearchError(String searchError) {
        this.searchError = searchError;
    }

    public void setPrevView(String prevView) {this.prevView = prevView;}

    @Override
    public String toString() {
        return "SignupState{" +
                "searchCriteria='" + searchCriteria + '\'' +
                " }";
        }
    }
