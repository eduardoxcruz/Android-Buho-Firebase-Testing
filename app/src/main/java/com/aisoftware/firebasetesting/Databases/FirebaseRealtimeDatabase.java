package com.aisoftware.firebasetesting.Databases;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseRealtimeDatabase {

    private FirebaseDatabase database;
    private DatabaseReference DatabaseReference;

    public FirebaseRealtimeDatabase(){

        this.database = FirebaseDatabase.getInstance();
        this.DatabaseReference = database.getReference();

    }

}
