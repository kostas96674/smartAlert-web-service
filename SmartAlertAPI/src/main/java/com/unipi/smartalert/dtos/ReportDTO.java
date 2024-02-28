package com.unipi.smartalert.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ReportDTO {

    Long categoryId;
    LocationDTO location;
    String timestamp;
    String description;

}
