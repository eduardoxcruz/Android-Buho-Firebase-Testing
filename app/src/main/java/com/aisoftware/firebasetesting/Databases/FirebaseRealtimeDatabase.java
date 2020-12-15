package com.aisoftware.firebasetesting.Databases;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

        DatabaseReference.child(rutaEnFirebase).addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {

                if (snapshot.exists())
                {
                    setDatoQueSeVaADevolver(snapshot.getValue().toString());
                }

                else
                {
                    setDatoQueSeVaADevolver("Error");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {
                setDatoQueSeVaADevolver("Error");
            }

        });

        return getDatoQueSeVaADevolver();
    }

    ////////////////////////////////////////////////
}
