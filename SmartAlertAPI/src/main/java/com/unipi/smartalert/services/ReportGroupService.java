package com.unipi.smartalert.services;

import com.unipi.smartalert.enums.GroupStatus;

public interface ReportGroupService {

    void changeStatus(long id, GroupStatus status);

}
