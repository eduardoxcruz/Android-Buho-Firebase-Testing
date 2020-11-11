package com.aisoftware.firebasetesting;

import android.content.Context;
import android.content.DialogInterface;

import com.github.javiersantos.appupdater.AppUpdater;
import com.github.javiersantos.appupdater.AppUpdaterUtils;
import com.github.javiersantos.appupdater.enums.AppUpdaterError;
import com.github.javiersantos.appupdater.enums.Display;
import com.github.javiersantos.appupdater.enums.UpdateFrom;
import com.github.javiersantos.appupdater.objects.Update;

public class Actualizaciones {

    public void ConsultarActualizacionesNuevasEnElServidor(Context contexto){

        AppUpdaterUtils consultadorDeActualizaciones = new AppUpdaterUtils(contexto)
            .setUpdateFrom(UpdateFrom.XML)
            .setUpdateXML("https://algoritmosinteligentes.000webhostapp.com/VersionesTQ/androidver.xml")
            .withListener(new AppUpdaterUtils.UpdateListener() {
                @Override
                public void onSuccess(Update update, Boolean isUpdateAvailable) {

                    if (isUpdateAvailable)
                    {

                    }

                    else
                    {

                    }

                }

                @Override
                public void onFailed(AppUpdaterError error) {

                }
            });
    }


    public void MostrarMensajeDeActualizacionDisponible(Context contexto){

        AppUpdater actualizador = new AppUpdater(contexto)
            .setDisplay(Display.DIALOG)
            .setTitleOnUpdateAvailable(R.string.HayUnaActualizacionDisponible)
            .setIcon(R.drawable.success)
            .setButtonUpdate(R.string.Actualizar)
            .setCancelable(false)
            .setButtonDismiss(null)
            .setButtonDoNotShowAgain(null)
            .setUpdateFrom(UpdateFrom.XML)
            .setUpdateXML("https://algoritmosinteligentes.000webhostapp.com/VersionesTQ/androidver.xml")
            .setButtonUpdateClickListener(new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
    }
}