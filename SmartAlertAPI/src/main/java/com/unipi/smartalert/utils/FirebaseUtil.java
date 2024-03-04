package com.unipi.smartalert.utils;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FirebaseUtil {

    public static final DatabaseReference DB_REF = FirebaseDatabase.getInstance().getReference();
    public static final String GROUPS = "groups";
    public static final String TOKENS = "tokens";

}
