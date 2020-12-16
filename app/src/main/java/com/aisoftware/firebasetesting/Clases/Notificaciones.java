package com.aisoftware.firebasetesting.Clases;

import android.content.Context;

import androidx.core.app.NotificationCompat;

public class Notificaciones {

    private Context contexto;
    private NotificationCompat.Builder notificationBuilder;

    public Notificaciones(Context Contexto){

        this.contexto = Contexto;
        this.notificationBuilder = new NotificationCompat.Builder(contexto, CHANNEL_ID);

    }

}
