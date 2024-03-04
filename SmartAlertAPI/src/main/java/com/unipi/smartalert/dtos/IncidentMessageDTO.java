package com.unipi.smartalert.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class IncidentMessageDTO {

    private LocationDTO location;
    private double radius;
    private Map<String, String> categoryNames;

}
