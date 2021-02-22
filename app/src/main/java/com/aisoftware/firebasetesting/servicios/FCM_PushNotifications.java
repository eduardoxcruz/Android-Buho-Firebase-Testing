package com.aisoftware.firebasetesting.servicios;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.aisoftware.firebasetesting.clases.Notificaciones;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import static android.content.ContentValues.TAG;

public class FCM_PushNotifications extends FirebaseMessagingService {

    private Context contexto;
    private NotificationCompat.Builder notificacion;

    public FCM_PushNotifications() {
    }

    public FCM_PushNotifications(Context contexto) {
        this.contexto = contexto;
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        if (remoteMessage.getNotification() != null) {
            int idDeLaNotificacion = obtenerIdDeUnTagDeFCM(remoteMessage.getNotification().getTag());

            notificacion = new Notificaciones(this).crearCuerpoDeNoitificacion(
                    this.getString(com.aisoftware.firebasetesting.R.string.CanalDeNotificacion_ID_NotificacionesPush),
                    remoteMessage.getNotification().getTitle(),
                    remoteMessage.getNotification().getBody());

            NotificationManagerCompat.from(this).notify(idDeLaNotificacion, notificacion.build());
        } else Log.d(TAG, "El cuerpo de la notificacion esta vacio");
    }

    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
    }

    public void suscribirATema(final String tema) {

        FirebaseMessaging.getInstance().subscribeToTopic(tema).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                String confirmacionDeSuscripcion = "Cliente no suscrito al tema.";

                if (task.isSuccessful()) confirmacionDeSuscripcion = "Cliente suscrito al tema.";

                Log.d(TAG, confirmacionDeSuscripcion);

            }
        });

    }

    private int obtenerIdDeUnTagDeFCM(String tag) {

        String tag_replaced = tag.replace("topic_key_", "");
        int id = 0;

        while (tag_replaced.length() > 3) {
            tag_replaced = tag_replaced.substring(0, tag_replaced.length() - 1);
        }

        return id = Integer.parseInt(tag_replaced);
    }
}
