package com.unipi.smartalert.services;

import com.unipi.smartalert.dtos.ReportGroupDTO;
import com.unipi.smartalert.enums.GroupStatus;

public interface ReportGroupService {

    void changeStatus(long id, GroupStatus status);
    ReportGroupDTO createDTO(long id);

}
