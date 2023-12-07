package interface_adapter.zip_search;

/**
 * The ZipSearchState stores and returns attributes that are used in other classes like
 * the ZipSearchView or ZipSearchViewModel
 * @author Diego
 */
public class ZipSearchState {
    private String zipSearchCriteria = "";
    private String zipSearchError = null;
    private String radSearchCriteria = "";
    private String radSearchError = null;

    /**
     * Instantiates the ZipSearchState
     * @param copy
     */
    public ZipSearchState(ZipSearchState copy) {
        zipSearchCriteria = copy.zipSearchCriteria;
        zipSearchError = copy.zipSearchError;
        radSearchCriteria = copy.radSearchCriteria;
        radSearchError = copy.radSearchError;
    }

    /**
     * Instantiates the SearchState without any parameters so that the previous constructor can work properly.
     */
    public ZipSearchState() {
    }

    /**
     * Returns the zipSearchCriteria attribute
     * @return zipSearchCriteria
     */
    public String getZipSearchCriteria() {
        return zipSearchCriteria;
    }

    /**
     * Returns the zipSearchError
     * @return zipSearchError
     */
    public String getZipSearchError() {
        return zipSearchError;
    }

    /**
     * Returns the radSearchCriteria
     * @return radSearchCriteria
     */
    public String getRadSearchCriteria() {
        return radSearchCriteria;
    }

    /**
     * Returns the radSearchError
     * @return radSearchError
     */
    public String getRadSearchError() {
        return radSearchError;
    }

    /**
     * Sets the zipSearchCriteria
     * @param zipSearchCriteria
     */
    public void setZipSearchCriteria(String zipSearchCriteria) {
        this.zipSearchCriteria = zipSearchCriteria;
    }

    /**
     * Sets the zipSearchError
     * @param zipSearchError
     */
    public void setZipSearchError(String zipSearchError) {
        this.zipSearchError = zipSearchError;
    }

    /**
     * Sets the radSearchCriteria
     * @param radSearchCriteria
     */
    public void setRadSearchCriteria(String radSearchCriteria) {this.radSearchCriteria = radSearchCriteria; }

    /**
     * Sets the radSearchError
     * @param radSearchError
     */
    public void setRadSearchError(String radSearchError) {this.radSearchError = radSearchError; }

    /**
     * Returns the SignupState and its zipSearchCriteria and radSearchCriteria attributes as a String.
     * @return A formatted String of the SignupState with its attributes.
     */
    @Override
    public String toString() {
        return "SignupState{" +
                "zipcode='" + zipSearchCriteria + '\'' +
                ", radius='" + radSearchCriteria + '\'' +
                '}';
    }
}
