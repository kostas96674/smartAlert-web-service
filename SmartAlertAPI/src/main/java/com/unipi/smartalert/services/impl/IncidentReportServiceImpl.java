package com.unipi.smartalert.services.impl;

import com.unipi.smartalert.dtos.ReportDTO;
import com.unipi.smartalert.mappers.IncidentReportMapper;
import com.unipi.smartalert.models.IncidentReport;
import com.unipi.smartalert.repositories.IncidentReportRepository;
import com.unipi.smartalert.services.IncidentReportService;
import com.unipi.smartalert.utils.ImageUtil;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@AllArgsConstructor
public class IncidentReportServiceImpl implements IncidentReportService {

    private final IncidentReportRepository repository;
    private final IncidentReportMapper mapper;
    private final EntityManager entityManager;

    @Transactional
    @Override
    public void saveReport(ReportDTO report, MultipartFile image) {
        IncidentReport savedReport = repository.save(mapper.mapToIncidentReport(report));
        entityManager.refresh(savedReport);

        if (image == null) {
            return;
        }

        byte[] byteArray;
        try {
            byteArray = image.getBytes();
        } catch (IOException e) {
            // TODO: throw a custom exception
            throw new RuntimeException(e);
        }

        String path = String.format("image-%d.jpg", savedReport.getId());
        ImageUtil.saveImageToDisk(byteArray, path);

        savedReport.setImagePath(path);
        repository.save(savedReport);
    }

}
