package com.unipi.smartalert.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReportGroupDTO {

    private String groupId;
    private String incidentCategoryName;
    private String lastReportTimestamp;
    private int reportCount;
    private LocationDTO location;

}
