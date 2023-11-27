package use_case.zip_search;

import entity.University;
import entity.UniversityFactory;
import org.json.JSONObject;
import use_case.search.*;

import java.util.List;

public class ZipSearchInteractor implements ZipSearchInputBoundary {

    public ZipSearchInteractor() {

    }

    @Override
    public void executeSearch(SearchInputData searchInputData) {
        String stringAccumulator = "";
        String input = searchInputData.getSearchCriteria();
        for (int i = 0; i < input.length(); i++) {
            String cursor = input.substring(i, i + 1);
            if (cursor.equals(" ")) {
                stringAccumulator += "%20";
            } else {
                stringAccumulator += cursor;
            }
        }
        //JSONObject query = searchDataAccessObject.searchQuery("school.name=" + stringAccumulator);
        //JSONObject metadata = query.getJSONObject("metadata");
        //if (metadata.getInt("total") == 0) {
            //searchPresenter.prepareResultsNotFoundView("Results not found!");
        //} else {
            //List<University> universityList = executeHelper(query.getJSONArray("results"));
            //SearchOutputData searchOutputData = new SearchOutputData(universityList);
            //searchPresenter.prepareSuccessView(searchOutputData);
        //}

    }
    public void executeBack(){
        //searchPresenter.prepareBackView();
    }
}