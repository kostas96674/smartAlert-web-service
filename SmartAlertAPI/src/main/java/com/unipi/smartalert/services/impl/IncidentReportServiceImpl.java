package com.unipi.smartalert.services.impl;

import com.unipi.smartalert.dtos.ReportDTO;
import com.unipi.smartalert.dtos.ReportGroupDTO;
import com.unipi.smartalert.exceptions.ErrorResponse;
import com.unipi.smartalert.exceptions.ResourceNotFoundException;
import com.unipi.smartalert.listeners.APIResponseListener;
import com.unipi.smartalert.mappers.IncidentReportMapper;
import com.unipi.smartalert.models.IncidentReport;
import com.unipi.smartalert.repositories.IncidentReportRepository;
import com.unipi.smartalert.services.FirebaseService;
import com.unipi.smartalert.services.IncidentReportService;
import com.unipi.smartalert.services.ReportGroupService;
import com.unipi.smartalert.utils.ImageUtil;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@AllArgsConstructor
public class IncidentReportServiceImpl implements IncidentReportService {

    private final IncidentReportRepository repository;
    private final IncidentReportMapper mapper;
    private final EntityManager entityManager;
    private final FirebaseService firebaseService;
    private final ReportGroupService groupService;
    private static final Logger logger = LoggerFactory.getLogger(IncidentReportServiceImpl.class);

    @Transactional
    @Override
    public void saveReport(ReportDTO report, MultipartFile image) {
        IncidentReport savedReport = repository.save(mapper.mapToIncidentReport(report));
        entityManager.refresh(savedReport);

        if (image != null) {
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

        // Create a ReportGroupDTO based on the savedReport group id
        ReportGroupDTO groupDTO = groupService.createDTO(savedReport.getGroupId());

        // Update firebase
        firebaseService.writeToDatabaseAsync(groupDTO, new APIResponseListener<>() {
            @Override
            public void onSuccessfulResponse(Void responseObject) {
                logger.info("Successfully added Report Group with ID {} to the Realtime database.", groupDTO.getGroupId());
            }

            @Override
            public void onFailure(ErrorResponse e) {
                logger.error("Failed to insert Report Group with ID {} to the Realtime database. Error: {}", groupDTO.getGroupId(), e.getMessage());
            }
        });

    }

    @Override
    public byte[] getImage(long id) {

        IncidentReport report = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Report with id " + id + " was not found")
        );

        String imgPath = report.getImagePath();
        if (imgPath == null || imgPath.trim().isEmpty()) {
            throw new ResourceNotFoundException("Image not found for report with id " + id);
        }

        return ImageUtil.readImageFromDisk(imgPath);
    }

}
