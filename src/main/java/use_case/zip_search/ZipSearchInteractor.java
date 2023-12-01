// Author: Diego
package use_case.zip_search;

import entity.University;
import entity.UniversityFactory;
import org.json.JSONException;
import org.json.JSONObject;
import use_case.search.*;

import java.util.List;

public class ZipSearchInteractor implements ZipSearchInputBoundary {
    final ZipSearchUserDataAccessInterface zipSearchDataAccessObject;
    final ZipSearchOutputBoundary zipSearchPresenter;
    final UniversityFactory universityFactory;
    public ZipSearchInteractor(ZipSearchUserDataAccessInterface zipSearchUserDataAccessInterface,
            ZipSearchOutputBoundary zipSearchPresenter, UniversityFactory universityFactory) {
        this.zipSearchDataAccessObject = zipSearchUserDataAccessInterface;
        this.zipSearchPresenter = zipSearchPresenter;
        this.universityFactory = universityFactory;
    }

    @Override
    public void executeSearch(ZipSearchInputData zipSearchInputData) {
        String stringAccumulator = "";
        String zipCodeInput = zipSearchInputData.getZipSearch();
        String radInput = zipSearchInputData.getRadSearch();
        for (int i = 0; i < zipCodeInput.length(); i++) {
            String cursor = zipCodeInput.substring(i, i + 1);
            if (cursor.equals(" ")) {
                stringAccumulator += "%20";
            } else {
                stringAccumulator += cursor;
            }
        }
        try {
            JSONObject query = zipSearchDataAccessObject.searchQuery("school.name=" + stringAccumulator);
            JSONObject metadata = query.getJSONObject("metadata");
            if (metadata.getInt("total") == 0) {
                zipSearchPresenter.prepareResultsNotFoundView("Results not found!");
            } else {
                //List<University> universityList = executeHelper(query.getJSONArray("results"));
                //SearchOutputData searchOutputData = new SearchOutputData(universityList);
                //searchPresenter.prepareSuccessView(searchOutputData);
            }
        } catch (JSONException e) {
            //searchPresenter.prepareResultsNotFoundView("JSON Error! (" + e + ")");
            }

    }
    public void executeBack(){
        zipSearchPresenter.prepareBackView();
    }
}