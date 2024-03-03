package com.unipi.smartalert.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportDTO {

    private Long id;
    private Long categoryId;
    private LocationDTO location;
    private String timestamp;
    private String description;
    private UserDTO sender;
    private boolean hasImage;

}
