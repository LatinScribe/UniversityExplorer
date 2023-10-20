package api.sping_boot_rest;


import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CreateTaskInput {
    @JsonProperty(required = true)
    private String title;

    @JsonProperty(required = true)
    private String description;

    @JsonProperty(required = true)
    private String status;

    @JsonProperty(value = "is_important", required = true)
    private boolean isImportant;

    @JsonProperty(value = "date", required = true)
    private String dueDate;

    @JsonProperty(value = "user", required = true)
    private String userId;

    // Getters and setters here...
}