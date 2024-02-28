package com.unipi.smartalert.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReportDTO {

    Long categoryId;
    LocationDTO location;
    String timestamp;
    String description;

}
