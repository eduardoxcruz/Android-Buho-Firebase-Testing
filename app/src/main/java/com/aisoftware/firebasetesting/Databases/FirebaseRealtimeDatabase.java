package com.aisoftware.firebasetesting.Databases;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseRealtimeDatabase {

    private final FirebaseDatabase database;
    private final DatabaseReference DatabaseReference;

    public FirebaseRealtimeDatabase(){

        this.database = FirebaseDatabase.getInstance();
        this.DatabaseReference = database.getReference();

    }

    public String ObtenerDato(String rutaEnFirebase){

    }
}
