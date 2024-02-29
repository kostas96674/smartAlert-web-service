package com.unipi.smartalert.services;

import com.unipi.smartalert.dtos.ReportGroupDTO;

public interface FirebaseService {

    void writeToDatabase(ReportGroupDTO reportGroupDTO);

}
