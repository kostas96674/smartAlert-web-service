package com.unipi.smartalert.services;

import com.unipi.smartalert.dtos.ReportGroupDTO;
import com.unipi.smartalert.enums.GroupStatus;

import java.util.List;

public interface ReportGroupService {

    void changeStatus(long id, GroupStatus status);
    ReportGroupDTO createDTO(long id);
    List<ReportGroupDTO> findAllAcceptedGroupsWithin24Hours();

}
