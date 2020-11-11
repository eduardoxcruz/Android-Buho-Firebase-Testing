package com.aisoftware.firebasetesting;

import android.content.Context;

import com.github.javiersantos.appupdater.AppUpdaterUtils;
import com.github.javiersantos.appupdater.enums.AppUpdaterError;
import com.github.javiersantos.appupdater.enums.UpdateFrom;
import com.github.javiersantos.appupdater.objects.Update;

public class Actualizaciones {

    public void ConsultarActualizacionesNuevasEnElServidor(final Context contexto){

        AppUpdaterUtils consultadorDeActualizaciones = new AppUpdaterUtils(contexto)
                .setUpdateFrom(UpdateFrom.XML).
                        setUpdateXML("https://algoritmosinteligentes.000webhostapp.com/VersionesTQ/androidver.xml")
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

}