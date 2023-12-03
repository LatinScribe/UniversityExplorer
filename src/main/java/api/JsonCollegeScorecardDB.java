package api;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * This file contains the methods for accessing data through the College Scorecard API
 * All methods return a JSON object
 * To get started, try running main and playing around with the basic query method
 * <p>
 * To properly understand this file, highly recommended that you read the documentation found at
 * CollegeScorecardAccess.md
 * <p>
 * NOTE: Requires internet access!
 *
 * @author Henry
 */
public class JsonCollegeScorecardDB implements CollegeScorecardDB {

    // Starting constants
    String API_TOKEN = "AwypPp5bIHx6H4EevsB5sFs4gw5NK1M7SNc4eXwl";
    String API_ADDRESS = "https://api.data.gov/ed/collegescorecard/v1/";


    /**
     * helper method for building the query with the constructed url
     *
     * @param url query url
     * @return constructed response body
     */
    @NotNull
    private JSONObject getJsonObject(String url) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            Response response = client.newCall(request).execute();
            System.out.println(response);
            assert response.body() != null;
            return new JSONObject(response.body().string());

//            if (responseBody.getInt("status_code") == 200) {
//                JSONObject grade = responseBody.getJSONObject("grade");
//                return grade;
//            } else {
//                throw new RuntimeException(responseBody.getString("message"));
//            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Sends an HTTP request to query the CollegeScoreCard API for data
     *
     * @param endpoint           what part of database to query, such as schools
     * @param fileFormat         type of return requested, such as json
     * @param queryParameters    see CollegeScorecardAccess.md for details
     * @param optionalParameters see CollegeScorecardAccess.md for details
     * @return the Json object containing the retrieved data
     */
    @Override
    public JSONObject basicQuery(String endpoint, String fileFormat, String queryParameters, String optionalParameters) {
        // A more customisable basic query builder
        // create the query url
        String url = API_ADDRESS + endpoint + fileFormat + "?" + queryParameters + "&" + optionalParameters + "&api_key=" + API_TOKEN;
        return getJsonObject(url);
    }

    /**
     * Sends an HTTP request to query the CollegeScoreCard API for data
     *
     * @param queryParameters    see CollegeScorecardAccess.md for details
     * @param optionalParameters see CollegeScorecardAccess.md for details
     * @return the Json object containing the retrieved data
     */
    @Override
    public JSONObject basicQuery(String queryParameters, String optionalParameters) {
        // less customisable query, assumes you are querying for schools and returning a json
        // recommend to use this
        // create the query url
        String url = API_ADDRESS + "schools.json?" + queryParameters + "&" + optionalParameters + "&api_key=" + API_TOKEN;
        return getJsonObject(url);
    }

    /**
     * Sends an HTTP request to query the CollegeScoreCard API for data
     * Specifically queries for universities within radius miles
     * from the given zip code
     *
     * @param zipcode zip code to center the search radius at
     * @param radius  radius to search in MILES
     * @return the Json object containing the retrieved data
     */
    @Override
    public JSONObject zipcodeQuery(String zipcode, String radius) {
        // query a radius around a zip code for institutions
        // NOTE: Make sure to add "mi" at the end of radius!
        // create the query url
        String url = API_ADDRESS + "schools.json?" + "zip=" + zipcode + "&" + "distance=" + radius + "&fields=id,school.name,2013.student.size" + "&fields=id,school.name,school.state,school.city,admissions.admission_rate.overall,cost.tuition.in_state,cost.tuition.out_of_state,admissions.sat_scores.average.overall,admissions.act_scores.midpoint.cumulative,school.school_url" + "&api_key=" + API_TOKEN;
        return getJsonObject(url);
    }
}
