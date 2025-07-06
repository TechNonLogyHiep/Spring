package org.example.baitap_apispringboot.dto.req;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class CourseReq {
    @Size(min = 2, max = 200, message = "INVALID_NAME_COURSE")
    String name;

    @Min(value = 0, message = "INVALID_NUM_SESSIONS")
    Integer numSessions = 0;

    @Min(value = 0, message = "INVALID_NUM_HOURS")
    Integer numHours = 0;
    String description;
    Integer category_id;
}
