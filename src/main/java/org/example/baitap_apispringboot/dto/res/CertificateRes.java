package org.example.baitap_apispringboot.dto.res;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.baitap_apispringboot.entity.CertificateType;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class CertificateRes {
    int certificate_id;
    CertificateType type;
    String studentName;
    LocalDate issueDate;
    boolean achieved;
    String courseName;
}
