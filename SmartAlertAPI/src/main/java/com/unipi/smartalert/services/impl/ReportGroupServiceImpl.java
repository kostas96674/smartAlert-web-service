package com.unipi.smartalert.services.impl;

import com.unipi.smartalert.dtos.ReportGroupDTO;
import com.unipi.smartalert.enums.GroupStatus;
import com.unipi.smartalert.exceptions.ActionNotAllowedException;
import com.unipi.smartalert.exceptions.ResourceNotFoundException;
import com.unipi.smartalert.mappers.ReportGroupMapper;
import com.unipi.smartalert.models.ReportGroup;
import com.unipi.smartalert.repositories.ReportGroupRepository;
import com.unipi.smartalert.services.ReportGroupService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReportGroupServiceImpl implements ReportGroupService {

    private final ReportGroupRepository repository;
    private final ReportGroupMapper groupMapper;

    @Transactional
    @Override
    public void changeStatus(long id, GroupStatus newStatus) {

        Optional<ReportGroup> reportGroupOptional = repository.findById(id);

        if (reportGroupOptional.isEmpty()) throw new ResourceNotFoundException("Report Group with id " + id + " was not found");
        ReportGroup reportGroup = reportGroupOptional.get();

        // Check if the report group is in 'PENDING' state, otherwise throw exception
        if (!reportGroup.getStatus().equals(GroupStatus.PENDING)) throw new ActionNotAllowedException("You cannot change the status of a non-pending report group");

        reportGroup.setStatus(newStatus);
        repository.save(reportGroup);

        // TODO: Update Firebase and inform the users

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

}
