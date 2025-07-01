package org.example.baitap_apispringboot.dto.res;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class CourseRes {
    int id;
    String name;
    Integer numSessions;
    Integer numHours;
    String description;
    String categoryName;
}
