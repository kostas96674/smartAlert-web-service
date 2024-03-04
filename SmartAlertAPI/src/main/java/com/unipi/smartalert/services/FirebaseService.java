package com.unipi.smartalert.services;

import com.unipi.smartalert.dtos.ReportGroupDTO;
import com.unipi.smartalert.dtos.IncidentMessageDTO;
import com.unipi.smartalert.listeners.APIResponseListener;
import lombok.NonNull;

public interface FirebaseService {

    void writeToDatabaseAsync(ReportGroupDTO reportGroupDTO, @NonNull APIResponseListener<Void> listener);
    void removeGroupFromDatabaseAsync(long groupId, @NonNull APIResponseListener<Void> listener);
    void sendMessageAsync(IncidentMessageDTO incidentMessageDTO, @NonNull APIResponseListener<Void> listener);

}
