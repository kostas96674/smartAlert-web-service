package com.unipi.smartalert.services.impl;

import com.unipi.smartalert.dtos.ReportDTO;
import com.unipi.smartalert.dtos.ReportGroupDTO;
import com.unipi.smartalert.enums.GroupStatus;
import com.unipi.smartalert.exceptions.ActionNotAllowedException;
import com.unipi.smartalert.exceptions.ErrorResponse;
import com.unipi.smartalert.exceptions.ResourceNotFoundException;
import com.unipi.smartalert.listeners.APIResponseListener;
import com.unipi.smartalert.mappers.IncidentReportMapper;
import com.unipi.smartalert.mappers.ReportGroupMapper;
import com.unipi.smartalert.models.ReportGroup;
import com.unipi.smartalert.repositories.ReportGroupRepository;
import com.unipi.smartalert.services.FirebaseService;
import com.unipi.smartalert.services.ReportGroupService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReportGroupServiceImpl implements ReportGroupService {

    private final ReportGroupRepository repository;
    private final ReportGroupMapper groupMapper;
    private final IncidentReportMapper reportMapper;
    private final FirebaseService firebaseService;
    private static final Logger logger = LoggerFactory.getLogger(ReportGroupServiceImpl.class);

    @Override
    public void changeStatus(long id, GroupStatus newStatus) {

        Optional<ReportGroup> reportGroupOptional = repository.findById(id);

        if (reportGroupOptional.isEmpty()) throw new ResourceNotFoundException(String.format("Report group with id %d was not found", id));
        ReportGroup reportGroup = reportGroupOptional.get();

        // Check if the report group is in 'PENDING' state, otherwise throw exception
        if (!reportGroup.getStatus().equals(GroupStatus.PENDING)) throw new ActionNotAllowedException("You cannot change the status of a non-pending report group");

        // Remove the group from Firebase Realtime database
        firebaseService.removeGroupFromDatabaseAsync(reportGroup.getId(), new APIResponseListener<>() {
            @Override
            public void onSuccessfulResponse(Void responseObject) {
                logger.info("Successfully removed report group with ID {} from Firebase Realtime database.", reportGroup.getId());

                // Update group status in our database (postgres)
                reportGroup.setStatus(newStatus);
                repository.save(reportGroup);

                if (!reportGroup.getStatus().equals(GroupStatus.ACCEPTED)) return;

                // TODO: Inform the users
            }

            @Override
            public void onFailure(ErrorResponse e) {
                logger.error("Failed to remove report group with ID {} from Firebase Realtime database.", reportGroup.getId());
            }
        });

    }

    @Override
    public ReportGroupDTO createDTO(long id) {

        Optional<ReportGroup> reportGroupOptional = repository.findById(id);

        if (reportGroupOptional.isEmpty()) throw new ResourceNotFoundException("Report Group with id " + id + " was not found");
        ReportGroup reportGroup = reportGroupOptional.get();

        return groupMapper.mapToDTO(reportGroup);
    }

    @Override
    public List<ReportGroupDTO> findAllAcceptedGroupsWithin24Hours() {
        LocalDateTime twentyFourHoursAgo = LocalDateTime.now().minusHours(24);
        List<ReportGroup> reportGroups = repository.findAllAcceptedGroupsWithin24Hours(twentyFourHoursAgo);
        return reportGroups.stream().map(groupMapper::mapToDTO).toList();
    }

    @Override
    public List<ReportDTO> getReportsByGroupId(long id) {
        ReportGroup reportGroup = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Group with id " + id + " was not found."));
        return reportGroup.getReports().stream().map(reportMapper::mapToDTO).toList();
    }

}
