//Author: André
package entity;

import org.junit.Test;

public class CommonUniversityTest {
    University university = new CommonUniversity(1, "tester", "NY", "New York", 50.3, 5000, 10000, 750, 250, "test.com");
    @Test
    public void testUniSimple() {
       assert (university instanceof CommonUniversity);
    }

    @Test
    public void testGetSchoolID() {
        assert (university.getSchoolID() == 1);
    }

    @Test
    public void testGetSchoolName() {
        assert (university.getSchoolName().equals("tester"));
    }

    @Test
    public void testGetState() {
        assert (university.getState().equals("NY"));
    }

    @Test
    public void testGetCity() {
        assert (university.getCity().equals("New York"));
    }

    @Test
    public void testGetAdmissionRate() {
        assert (university.getAdmissionRate() == 50.3);
    }

    @Test
    public void testAverageInStateTuition() {
        assert (university.getAverageInStateTuition() == 5000);
    }

    @Test
    public void testAverageOutOfStateTuition() {
        University university = new CommonUniversity(1, "tester", "NY", "New York", 50.3, 5000, 10000, 750, 250, "test.com");
        assert (university.getAverageOutOfStateTuition() == 10000);
    }

    @Test
    public void testSATScore() {
        assert (university.getAverageSATScore() == 750.0);
    }

    @Test
    public void testACTScore() {
        University university = new CommonUniversity(1, "tester", "NY", "New York", 50.3, 5000, 10000, 750, 250, "test.com");
        assert (university.getAverageACTScore() == 250.0);
    }

    @Test
    public void testURL() {
        University university = new CommonUniversity(1, "tester", "NY", "New York", 50.3, 5000, 10000, 750, 250, "test.com");
        assert (university.getUrl().equals("test.com"));
    }

    @Test
    public void testToString() {
        University university = new CommonUniversity(1, "tester", "NY", "New York", 50.3, 5000, 10000, 750, 250, "test.com");
        assert (university.toString().equals("Institution Name: tester\nLocation: New York, NY\nAdmission Rate: 50.3\nAverage Tuition (In State): $5000\nAverage Tuition (Out of State): $10000\nAverage SAT Score: 750.0\nAverage ACT Score: 250.0\nURL: test.com"));
    }

    @Test
    public void testNullToString() {
        University university = new CommonUniversity(null, null, null, null, null, null, null, null, null, null);
        assert (university.toString().equals("Institution Name: N/A\nLocation: N/A, N/A\nAdmission Rate: N/A\nAverage Tuition (In State): N/A\nAverage Tuition (Out of State): N/A\nAverage SAT Score: N/A\nAverage ACT Score: N/A\nURL: N/A"));
    }
}
