// Author: Diego
package interface_adapter.zip_search;


public class ZipSearchState {
    private String zipSearchCriteria = "";
    private String zipSearchError = null;
    private String radSearchCriteria = "";
    private String radSearchError = null;

    public ZipSearchState(ZipSearchState copy) {
        zipSearchCriteria = copy.zipSearchCriteria;
        zipSearchError = copy.zipSearchError;
        radSearchCriteria = copy.radSearchCriteria;
        radSearchError = copy.radSearchError;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public ZipSearchState() {
    }

    public String getZipSearchCriteria() {
        return zipSearchCriteria;
    }

    public String getZipSearchError() {
        return zipSearchError;
    }

    public String getRadSearchCriteria() {
        return radSearchCriteria;
    }

    public String getRadSearchError() {
        return radSearchError;
    }

    public void setZipSearchCriteria(String zipSearchCriteria) {
        this.zipSearchCriteria = zipSearchCriteria;
    }

    public void setZipSearchError(String zipSearchError) {
        this.zipSearchError = zipSearchError;
    }

    public void setRadSearchCriteria(String radSearchCriteria) {this.radSearchCriteria = radSearchCriteria; }

    public void setRadSearchError(String radSearchError) {this.radSearchError = radSearchError; }

    @Override
    public String toString() {
        return "SignupState{" +
                "zipcode='" + zipSearchCriteria + '\'' +
                ", radius='" + radSearchCriteria + '\'' +
                '}';
    }
}
