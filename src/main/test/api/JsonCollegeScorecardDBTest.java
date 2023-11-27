package api;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonCollegeScorecardDBTest {

    @Test
    void basicQueryExtraParameters() {
        JsonCollegeScorecardDB db = new JsonCollegeScorecardDB();
        JSONObject result = db.basicQuery("schools", ".json", "school.degrees_awarded.predominant=2,3","fields=id,school.name,2013.student.size");
        assert (result != null);
        System.out.println(result);
    }

    @Test
    void testBasicQuery() {
        JsonCollegeScorecardDB db = new JsonCollegeScorecardDB();
        JSONObject result = db.basicQuery("school.degrees_awarded.predominant=2,3", "fields=id,school.name,2013.student.size");
        assert (result != null);
        System.out.println(result);
    }

    @Test
    void zipcodeQuery() {
        JsonCollegeScorecardDB db = new JsonCollegeScorecardDB();
        JSONObject result = db.zipcodeQuery("02138", "5mi");
        assert (result != null);
        System.out.println(result);
    }
}