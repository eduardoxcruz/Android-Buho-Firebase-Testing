package com.aisoftware.firebasetesting.Servicios;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import static android.content.ContentValues.TAG;

public class FCM_PushNotifications extends FirebaseMessagingService {

    private Context contexto;

    public FCM_PushNotifications() {
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
    }

    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
    }

    public FCM_PushNotifications(Context Contexto) {
        this.contexto = Contexto;
    }


    public void SuscribirATema(final String tema) {

        FirebaseMessaging.getInstance().subscribeToTopic(tema).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                String confirmacionDeSuscripcion = "Cliente no suscrito al tema.";

                if (task.isSuccessful()) confirmacionDeSuscripcion = "Cliente suscrito al tema.";

                Log.d(TAG, confirmacionDeSuscripcion);

            }
        });

    }

}
