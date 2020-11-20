package com.aisoftware.firebasetesting.Clases;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;

import androidx.annotation.NonNull;

import com.aisoftware.firebasetesting.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class Turno {

    private FirebaseDatabase database;
    private DatabaseReference refDatabase;
    private Alertas alerta;
    private Calendar calendario;
    private int horaActual;
    private String turnoActual;

    public Turno(){

        this.database = FirebaseDatabase.getInstance();
        this.refDatabase = database.getReference();
        this.alerta = new Alertas();
        this.calendario = Calendar.getInstance();
        this.horaActual = calendario.get(Calendar.HOUR_OF_DAY);

    }

    public String getTurnoActual(Context contexto, Activity activity) {

        BuscarElTurnoActual(contexto, activity);

        return turnoActual;
    }

    public void setTurnoActual(String value) {
        this.turnoActual = value;
    }
}
