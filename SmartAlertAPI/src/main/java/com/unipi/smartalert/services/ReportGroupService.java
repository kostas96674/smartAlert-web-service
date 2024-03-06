package com.unipi.smartalert.services;

import com.unipi.smartalert.dtos.ReportDTO;
import com.unipi.smartalert.dtos.ReportGroupDTO;
import com.unipi.smartalert.enums.GroupStatus;
import com.unipi.smartalert.models.ReportGroup;

import java.util.List;

public interface ReportGroupService {

    void changeStatus(long id, GroupStatus status);
    ReportGroupDTO createDTO(long id);
    List<ReportGroupDTO> findAllAcceptedGroupsWithin24Hours();
    List<ReportDTO> getReportsByGroupId(long id);
    List<ReportGroup> findAcceptedGroupsByMonthAndYear(int month, int year);

}
