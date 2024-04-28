package com.unipi.smartalert.services.impl;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutureCallback;
import com.google.api.core.ApiFutures;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.MulticastMessage;
import com.unipi.smartalert.dtos.IncidentMessageDTO;
import com.unipi.smartalert.dtos.ReportGroupDTO;
import com.unipi.smartalert.exceptions.ErrorResponse;
import com.unipi.smartalert.listeners.APIResponseListener;
import com.unipi.smartalert.services.FirebaseService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import static com.unipi.smartalert.utils.FirebaseUtil.*;

@AllArgsConstructor
@Service
public class FirebaseServiceImpl implements FirebaseService {

    private final Executor executor;
    private final FirebaseMessaging firebaseMessaging;
    private static final int MAX_MULTICAST_SIZE = 500;

    @Override
    public void writeToDatabaseAsync(ReportGroupDTO reportGroupDTO, @NonNull APIResponseListener<Void> listener) {
        ApiFuture<Void> apiFuture = DB_REF.child(GROUPS).child(String.valueOf(reportGroupDTO.getGroupId())).setValueAsync(reportGroupDTO);
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
        ApiFuture<Void> apiFuture = DB_REF.child(GROUPS).child(String.valueOf(groupId)).removeValueAsync();
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
    public void sendMessageAsync(IncidentMessageDTO incidentMessageDTO, @NonNull APIResponseListener<Void> listener) {

        getTokensAsync(new APIResponseListener<>() {
            @Override
            public void onSuccessfulResponse(List<String> responseObject) {

                int totalTokens = responseObject.size();
                int timesToSend = totalTokens % MAX_MULTICAST_SIZE == 0 ? totalTokens / MAX_MULTICAST_SIZE : (totalTokens / MAX_MULTICAST_SIZE) + 1;

                for (int i = 0; i < timesToSend; i++) {

                    // TODO: Split list if timesToSend > 1

                    MulticastMessage multicastMessage = MulticastMessage.builder()
                            .addAllTokens(responseObject)
                            .putData("longitude", String.valueOf(incidentMessageDTO.getLocation().getLongitude()))
                            .putData("latitude", String.valueOf(incidentMessageDTO.getLocation().getLatitude()))
                            .putData("radius", String.valueOf(incidentMessageDTO.getRadius()))
                            .putData("category", incidentMessageDTO.getCategoryNames().get("en"))
                            .putData("category-el", incidentMessageDTO.getCategoryNames().get("el"))
                            .build();

                    try {

                        firebaseMessaging.sendEachForMulticast(multicastMessage);

                    } catch (FirebaseMessagingException e) {
                        listener.onFailure(new ErrorResponse(LocalDateTime.now(), String.format("Firebase Messaging Error. HTTP response code: %d", e.getHttpResponse().getStatusCode()), e.getMessage()));
                    }

                }

                listener.onSuccessfulResponse(null);

            }

            @Override
            public void onFailure(ErrorResponse e) {
                listener.onFailure(new ErrorResponse(LocalDateTime.now(), e.getError(), e.getMessage()));
            }
        });

    }

    private void getTokensAsync(@NonNull APIResponseListener<List<String>> listener) {

        // Fetch the tokens from firebase
        List<String> tokens = new ArrayList<>();

        DB_REF.child(TOKENS).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot tokenSnapshot : dataSnapshot.getChildren()) {
                    String token = tokenSnapshot.getValue(String.class);
                    tokens.add(token);
                }
                listener.onSuccessfulResponse(tokens);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                listener.onFailure(new ErrorResponse(LocalDateTime.now(), "Firebase error", databaseError.getMessage()));
            }
        });

    }

}
