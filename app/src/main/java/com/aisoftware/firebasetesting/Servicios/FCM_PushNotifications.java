package com.aisoftware.firebasetesting.Servicios;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;

public class FCM_PushNotifications extends FirebaseMessagingService {

    private Context contexto;

    public FCM_PushNotifications() {
    }

    public FCM_PushNotifications(Context Contexto) {

        this.contexto = Contexto;

    }


    public void SuscribirATema(final String tema) {

        FirebaseMessaging.getInstance().subscribeToTopic(tema).addOnCompleteListener(new OnCompleteListener<Void>()
        {
            @Override
            public void onComplete(@NonNull Task<Void> task)
            {

                String confirmacion_de_Suscripcion = "Cliente suscrito correctamente al tema: " + tema;

                if(!task.isSuccessful()) confirmacion_de_Suscripcion = "No se pudo suscribir correctamente al tema: " + tema;

                Toast.makeText(contexto, confirmacion_de_Suscripcion, Toast.LENGTH_SHORT).show();

            }
        });

    }

}
