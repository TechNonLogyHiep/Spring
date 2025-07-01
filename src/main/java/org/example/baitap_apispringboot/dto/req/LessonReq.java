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
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LessonReq {

    @Size(max = 200,min = 3,message = "INVALID_NAME_LESSON")
    String title;
    String description;

    @Column(name = "num_hours")
    @Min(value = 0,message = "INVALID_NUM_HOURS")
    private Integer numHours = 0;
    int course_id;
}
