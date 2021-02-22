package com.aisoftware.firebasetesting.clases;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.aisoftware.firebasetesting.R;

public class Notificaciones {

    private Context contexto;
    private NotificationCompat.Builder constructorDeNotificacion;

    public Notificaciones(Context contexto) {
        this.contexto = contexto;
    }

    public void crearCanalDeNotificacion(int id, int nombreDelCanal, int descripcionDelCanal, int importancia) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel canalDeNotificacion = new NotificationChannel(contexto.getString(id), contexto.getString(nombreDelCanal), importancia);
            canalDeNotificacion.setDescription(contexto.getString(descripcionDelCanal));

            NotificationManager managerDeNotificacion = contexto.getSystemService(NotificationManager.class);
            managerDeNotificacion.createNotificationChannel(canalDeNotificacion);

        }

    }

    //TODO: Cambiar el icono pequeño de las notificaciones en la linea constructorDeNotificaciones.setSmallIcon()
    public NotificationCompat.Builder crearCuerpoDeNoitificacion(String idDelCanalDeNotificacion,
                                                                 CharSequence titulo,
                                                                 CharSequence texto) {
        constructorDeNotificacion = new NotificationCompat.Builder(contexto, idDelCanalDeNotificacion);
        constructorDeNotificacion.setSmallIcon(R.drawable.success)
                .setContentTitle(titulo)
                .setContentText(texto)
                .setPriority(NotificationCompat.PRIORITY_MAX);

        return constructorDeNotificacion;
    }

}
