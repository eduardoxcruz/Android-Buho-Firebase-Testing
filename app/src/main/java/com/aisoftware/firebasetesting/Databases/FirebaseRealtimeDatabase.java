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
    private String Dato;

    //Getters y Setters
    public String getDato() {
        return Dato;
    }

    public void setDato(String dato) {
        Dato = dato;
    }

    ////////////////////////////////////////////////


    //Constructores
    public FirebaseRealtimeDatabase(){

        this.database = FirebaseDatabase.getInstance();
        this.DatabaseReference = database.getReference();
        this.setDato(null);

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
                    setDato(snapshot.getValue().toString());
                }

                else
                {
                    setDato("Error");
                }


                DatabaseReference.removeEventListener(this);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {

                setDato("Error");
                DatabaseReference.removeEventListener(this);
            }

        });

        return getDato();
    }

    ////////////////////////////////////////////////
}
