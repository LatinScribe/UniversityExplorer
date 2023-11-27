// FOR REFERENCE ONLY, DO NOT USE IN PRODUCTION (managed by Henry)
package api.gradeAPI_for_reference;

import entity.gradeAPI_entities_for_reference.Grade;
import entity.gradeAPI_entities_for_reference.Team;
import org.json.JSONException;

public interface GradeDB {
    // Question: The dependency can go inwards, so I feel we can use the Grade class here (which is entity).
    Grade getGrade(String utorid, String course);

    Grade logGrade(String course, int grade) throws JSONException;

    Team formTeam(String name) throws JSONException;

    Team joinTeam(String name) throws JSONException;

    Team getMyTeam() throws JSONException;

    void leaveTeam() throws JSONException;

}
