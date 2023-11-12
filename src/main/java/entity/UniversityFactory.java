package entity;

import java.time.LocalDateTime;

public interface UniversityFactory {
    CommonUniversity create(String schoolName, String state, String city, Double admissionRate, Integer averageInStateTuition, Integer averageOutOfStateTuition);
}
