package com.aisoftware.firebasetesting.Clases;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class Notificaciones {

    private Context contexto;
    private NotificationCompat.Builder notificationBuilder;

    public Notificaciones(Context Contexto){

        this.contexto = Contexto;

    }

    public void CrearCanalDeNotificacion(int id, int nombre_del_canal, int descripcion_del_canal, int importancia){

        String ID = contexto.getString(id);
        CharSequence nombreDelCanal = contexto.getString(nombre_del_canal);
        String descripcionDelCanal = contexto.getString(descripcion_del_canal);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {

            NotificationChannel canalDeNotificacion = new NotificationChannel(ID, nombreDelCanal, importancia);
            canalDeNotificacion.setDescription(descripcionDelCanal);

            NotificationManager managerDeNotificacion = contexto.getSystemService(NotificationManager.class);
            managerDeNotificacion.createNotificationChannel(canalDeNotificacion);

        }

    }

}
