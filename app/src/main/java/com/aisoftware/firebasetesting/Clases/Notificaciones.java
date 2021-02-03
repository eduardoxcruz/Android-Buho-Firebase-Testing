package com.aisoftware.firebasetesting.Clases;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.aisoftware.firebasetesting.R;

public class Notificaciones {

    private Context contexto;
    private NotificationCompat.Builder constructorDeNotificacion;

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

    //TODO: Cambiar el icono pequeño de las notificaciones en la linea constructorDeNotificaciones.setSmallIcon()
    public NotificationCompat.Builder CrearCuerpoDeNoitificacion(String canalDeNotificacion_ID,
                                                                 CharSequence Titulo,
                                                                 CharSequence texto) {
        constructorDeNotificacion = new NotificationCompat.Builder(contexto, canalDeNotificacion_ID);
        constructorDeNotificacion.setSmallIcon(R.drawable.success)
                .setContentTitle(Titulo)
                .setContentText(texto)
                .setPriority(NotificationCompat.PRIORITY_MAX);

        return constructorDeNotificacion;
    }

}
