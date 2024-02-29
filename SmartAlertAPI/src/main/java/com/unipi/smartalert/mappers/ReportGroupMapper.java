package com.unipi.smartalert.mappers;

import com.unipi.smartalert.dtos.LocationDTO;
import com.unipi.smartalert.dtos.ReportGroupDTO;
import com.unipi.smartalert.models.ReportGroup;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ReportGroupMapper {

    public ReportGroupDTO mapToDTO(ReportGroup reportGroup) {

        Map<String, String> categories = new HashMap<>();
        reportGroup.getCategory().getNames().forEach(categoryName -> categories.put(categoryName.getLanguage(), categoryName.getName()));

        LocationDTO locationDTO = LocationDTO.builder()
                .longitude(reportGroup.getCentralPoint().getX())
                .latitude(reportGroup.getCentralPoint().getY())
                .build();


        return ReportGroupDTO.builder()
                .groupId(reportGroup.getId())
                .incidentCategory(categories)
                .lastReportTimestamp(reportGroup.getLastUpdated().toString())
                .reportCount(10)
                .location(locationDTO)
                .build();
    }

}
