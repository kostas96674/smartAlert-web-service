package com.unipi.smartalert.listeners;

import com.unipi.smartalert.exceptions.ErrorResponse;

public interface APIResponseListener<T> {

    void onSuccessfulResponse(T responseObject);
    void onFailure(ErrorResponse e);

}
