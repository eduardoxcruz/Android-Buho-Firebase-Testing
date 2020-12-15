package com.aisoftware.firebasetesting.Databases;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseRealtimeDatabase {

    private final FirebaseDatabase database;
    private final DatabaseReference DatabaseReference;
    private String DatoQueSeVaADevolver;

    //Getters y Setters
    public String getDatoQueSeVaADevolver() {
        return DatoQueSeVaADevolver;
    }

    public void setDatoQueSeVaADevolver(String datoQueSeVaADevolver) {
        DatoQueSeVaADevolver = datoQueSeVaADevolver;
    }

    ////////////////////////////////////////////////


    //Constructores
    public FirebaseRealtimeDatabase(){

        this.database = FirebaseDatabase.getInstance();
        this.DatabaseReference = database.getReference();
        this.setDatoQueSeVaADevolver(null);
    }

    ////////////////////////////////////////////////


    //Metodos
    public String ObtenerDatoEnUnSoloEvento(String rutaEnFirebase){

    }

    ////////////////////////////////////////////////
}
