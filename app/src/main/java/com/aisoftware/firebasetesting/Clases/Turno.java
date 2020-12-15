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
    private Context contexto;

    public Turno(Context Contexto){

        this.database = FirebaseDatabase.getInstance();
        this.refDatabase = database.getReference();
        this.contexto = Contexto;
        this.alerta = new Alertas(contexto);
        this.calendario = Calendar.getInstance();
        this.horaActual = calendario.get(Calendar.HOUR_OF_DAY);

    }

    public String getTurnoActual() {
        return turnoActual;
    }

    public void setTurnoActual(String value) {
        this.turnoActual = value;
    }

    private void BuscarElTurnoActual(final Activity activity){

        refDatabase.child("/Turno").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapTurno) {

                if(snapTurno.exists())
                {

                    final int numeroDeTurnosRegistrados = Integer.parseInt(snapTurno.child("Turnos").getValue().toString());
                    int turnoQuePuedeDurarDespuesDeLas24hrs = 1;
                    int duracionDeUnTurno = 24 / numeroDeTurnosRegistrados;

                    while (turnoQuePuedeDurarDespuesDeLas24hrs <= numeroDeTurnosRegistrados){

                        int inicioDelTurno = Integer.parseInt(snapTurno.child("I" + String.valueOf(turnoQuePuedeDurarDespuesDeLas24hrs)).getValue().toString());
                        int finDelTurno = Integer.parseInt(snapTurno.child("F" + String.valueOf(turnoQuePuedeDurarDespuesDeLas24hrs)).getValue().toString());

                        if ((inicioDelTurno + duracionDeUnTurno) > 24 ){

                            if(horaActual >= inicioDelTurno || horaActual >= 0 && horaActual < finDelTurno) {

                                setTurnoActual(String.valueOf(turnoQuePuedeDurarDespuesDeLas24hrs));
                                break;

                            }

                            else{

                                int turnoQueNoDuraDespuesDeLas24hrs = 1;

                                while (turnoQueNoDuraDespuesDeLas24hrs <= numeroDeTurnosRegistrados){

                                    int inicioDelTurnoNo24h = Integer.parseInt(snapTurno.child("I" + String.valueOf(turnoQueNoDuraDespuesDeLas24hrs)).getValue().toString());
                                    int finDelTurnoNo24h = Integer.parseInt(snapTurno.child("F" + String.valueOf(turnoQueNoDuraDespuesDeLas24hrs)).getValue().toString());

                                    if (turnoQueNoDuraDespuesDeLas24hrs == turnoQuePuedeDurarDespuesDeLas24hrs) turnoQueNoDuraDespuesDeLas24hrs++;

                                    else {

                                        if (turnoQueNoDuraDespuesDeLas24hrs + 1 <= numeroDeTurnosRegistrados){

                                            if (horaActual >= inicioDelTurnoNo24h && horaActual < finDelTurnoNo24h) {

                                                setTurnoActual(String.valueOf(turnoQueNoDuraDespuesDeLas24hrs));
                                                break;

                                            }

                                            else turnoQueNoDuraDespuesDeLas24hrs++;

                                        }

                                        else {

                                            setTurnoActual(String.valueOf(turnoQueNoDuraDespuesDeLas24hrs));
                                            break;

                                        }

                                    }

                                }

                            }

                        }

                        else {

                            if(horaActual >= inicioDelTurno && horaActual < finDelTurno) {

                                setTurnoActual(String.valueOf(turnoQuePuedeDurarDespuesDeLas24hrs));
                                break;

                            }

                            else{

                                if (turnoQuePuedeDurarDespuesDeLas24hrs == numeroDeTurnosRegistrados){

                                    alerta.GenerarAlertaDeError(
                                            R.string.NoSePudoObtenerElTurnoActual,
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    activity.finish();
                                                }
                                            },
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    activity.finish();
                                                }
                                            });

                                }

                                else turnoQuePuedeDurarDespuesDeLas24hrs++;

                            }

                        }
                    }

                }

                else {
                    alerta.GenerarAlertaDeError(
                            R.string.ErrorAlObtenerLosHorariosDeLosTurnos,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    activity.finish();
                                }
                            },
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    activity.finish();
                                }
                            }
                    );
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}

        });

    }

}
