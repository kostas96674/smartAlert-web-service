package com.unipi.smartalert.mappers;

import com.unipi.smartalert.dtos.ReportDTO;
import com.unipi.smartalert.models.IncidentReport;
import com.unipi.smartalert.services.IncidentCategoryService;
import com.unipi.smartalert.services.UserService;
import com.unipi.smartalert.utils.SecurityUtil;
import lombok.AllArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@AllArgsConstructor
public class IncidentReportMapper {

    private final IncidentCategoryService incidentCategoryService;
    private final GeometryFactory geometryFactory;
    private final UserService userService;

    public IncidentReport mapToIncidentReport(ReportDTO reportDTO) {
        Point point = geometryFactory.createPoint(
                new Coordinate(
                        reportDTO.getLocation().getLatitude(),
                        reportDTO.getLocation().getLongitude())
        );
        point.setSRID(4326);

        return IncidentReport.builder()
                .user(userService.findByEmail(SecurityUtil.getAuthenticatedUserEmail()))
                .category(incidentCategoryService.findById(reportDTO.getCategoryId()))
                .location(point)
                .description(reportDTO.getDescription())
                .createdAt(Timestamp.valueOf(reportDTO.getTimestamp()))
                .build();
    }

}
