package com.aisoftware.firebasetesting.Clases;

import android.content.Context;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

    public String _TurnoActual(Context contexto) {
        return turnoActual;
    }

    public void _TurnoActual(String value) {
        this.turnoActual = value;
    }
}
