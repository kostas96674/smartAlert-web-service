package com.unipi.smartalert.services.impl;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutureCallback;
import com.google.api.core.ApiFutures;
import com.unipi.smartalert.dtos.ReportGroupDTO;
import com.unipi.smartalert.exceptions.ErrorResponse;
import com.unipi.smartalert.listeners.APIResponseListener;
import com.unipi.smartalert.services.FirebaseService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.Executor;

import static com.unipi.smartalert.utils.FirebaseUtil.DB_REF;
import static com.unipi.smartalert.utils.FirebaseUtil.REPORT_GROUPS_PATH;

@AllArgsConstructor
@Service
public class FirebaseServiceImpl implements FirebaseService {

    private final Executor executor;


    @Override
    public void writeToDatabaseAsync(ReportGroupDTO reportGroupDTO, @NonNull  APIResponseListener<Void> listener) {
        ApiFuture<Void> apiFuture = DB_REF.child(REPORT_GROUPS_PATH).child(String.valueOf(reportGroupDTO.getGroupId())).setValueAsync(reportGroupDTO);
        ApiFutures.addCallback(apiFuture, new ApiFutureCallback<>() {
            @Override
            public void onFailure(Throwable throwable) {
                listener.onFailure(new ErrorResponse(LocalDateTime.now(), "Firebase Error", throwable.getMessage()));
            }

            @Override
            public void onSuccess(Void unused) {
               listener.onSuccessfulResponse(null);
            }
        }, executor);
    }

    @Override
    public void removeGroupFromDatabaseAsync(long groupId, @NonNull APIResponseListener<Void> listener) {
        ApiFuture<Void> apiFuture = DB_REF.child(REPORT_GROUPS_PATH).child(String.valueOf(groupId)).removeValueAsync();
        ApiFutures.addCallback(apiFuture, new ApiFutureCallback<>() {
            @Override
            public void onFailure(Throwable throwable) {
                listener.onFailure(new ErrorResponse(LocalDateTime.now(), "Firebase Error", throwable.getMessage()));
            }

            @Override
            public void onSuccess(Void unused) {
                listener.onSuccessfulResponse(null);
            }
        });
    }

}
