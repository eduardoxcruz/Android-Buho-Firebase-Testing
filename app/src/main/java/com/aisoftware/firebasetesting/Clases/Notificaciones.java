package com.aisoftware.firebasetesting.Clases;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.aisoftware.firebasetesting.R;

public class Notificaciones {

    private Context contexto;
    private NotificationCompat.Builder constructorDeNotificacion;
    private NotificationManagerCompat gestorDeNotificacion;

    public Notificaciones(Context Contexto){

        this.contexto = Contexto;
        this.gestorDeNotificacion = NotificationManagerCompat.from(contexto);

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
    public void CrearNoitificacion(int canalDeNotificacion_ID, int notificacionID, int Titulo, CharSequence texto, int prioridad){

        String ID = contexto.getString(canalDeNotificacion_ID);
        CharSequence titulo = contexto.getString(Titulo);

        constructorDeNotificacion = new NotificationCompat.Builder(contexto, ID);
        constructorDeNotificacion.setSmallIcon(R.drawable.success)
                .setContentTitle(titulo)
                .setContentText(texto)
                .setPriority(prioridad);

        gestorDeNotificacion.notify(notificacionID, constructorDeNotificacion.build());

    }

}
