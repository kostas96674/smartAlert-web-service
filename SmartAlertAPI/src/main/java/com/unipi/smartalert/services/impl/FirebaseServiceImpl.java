package com.unipi.smartalert.services.impl;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutureCallback;
import com.google.api.core.ApiFutures;
import com.unipi.smartalert.dtos.ReportGroupDTO;
import com.unipi.smartalert.services.FirebaseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executor;

import static com.unipi.smartalert.utils.FirebaseUtil.DB_REF;
import static com.unipi.smartalert.utils.FirebaseUtil.REPORT_GROUPS_PATH;

@AllArgsConstructor
@Service
public class FirebaseServiceImpl implements FirebaseService {

    private final Executor executor;


    @Override
    public void writeToDatabase(ReportGroupDTO reportGroupDTO) {
        ApiFuture<Void> apiFuture = DB_REF.child(REPORT_GROUPS_PATH).child(String.valueOf(reportGroupDTO.getGroupId())).setValueAsync(reportGroupDTO);
        ApiFutures.addCallback(apiFuture, new ApiFutureCallback<>() {
            @Override
            public void onFailure(Throwable throwable) {
                // Todo: Log the error
                System.out.println("Error: " + throwable.getMessage());
            }

            @Override
            public void onSuccess(Void unused) {
                // Todo: Log the success
                System.out.println("Value from android app was set successfully.");
            }
        }, executor);
    }

}
