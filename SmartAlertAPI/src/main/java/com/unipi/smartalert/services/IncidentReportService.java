package com.unipi.smartalert.services;

import com.unipi.smartalert.dtos.ReportDTO;
import org.springframework.web.multipart.MultipartFile;

public interface IncidentReportService {

    void saveReport(ReportDTO report, MultipartFile image);

}
