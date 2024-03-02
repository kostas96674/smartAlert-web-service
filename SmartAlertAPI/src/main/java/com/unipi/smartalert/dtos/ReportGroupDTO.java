package com.unipi.smartalert.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportGroupDTO {

    private long groupId;
    private Map<String, String> incidentCategory;
    private String lastReportTimestamp;
    private int reportCount;
    private LocationDTO location;

}
