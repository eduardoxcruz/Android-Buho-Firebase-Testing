package com.aisoftware.firebasetesting;

import android.content.Context;
import android.content.DialogInterface;

import com.github.javiersantos.appupdater.AppUpdater;
import com.github.javiersantos.appupdater.AppUpdaterUtils;
import com.github.javiersantos.appupdater.enums.Display;
import com.github.javiersantos.appupdater.enums.UpdateFrom;

public class Actualizaciones {

    public void ConsultarActualizacionesNuevasEnElServidor(Context contexto, AppUpdaterUtils.UpdateListener accionDelListener){

        new AppUpdaterUtils(contexto)
                .setUpdateFrom(UpdateFrom.XML)
                .setUpdateXML("https://algoritmosinteligentes.000webhostapp.com/VersionesTQ/androidver.xml")
                .withListener(accionDelListener)
                .start();

    }

    public void MostrarMensajeDeActualizacionDisponible(Context contexto, DialogInterface.OnClickListener accionesDelBotonActualizar, DialogInterface.OnClickListener accionesDelBotonCancelar){

        new AppUpdater(contexto)
                .setDisplay(Display.DIALOG)
                .setTitleOnUpdateAvailable(R.string.HayUnaActualizacionDisponible)
                .setIcon(R.drawable.success)
                .setCancelable(false)
                .setButtonUpdate(R.string.Actualizar)
                .setButtonDismiss(R.string.Cancelar)
                .setButtonDoNotShowAgain(null)
                .setUpdateFrom(UpdateFrom.XML)
                .setUpdateXML("https://algoritmosinteligentes.000webhostapp.com/VersionesTQ/androidver.xml")
                .setButtonUpdateClickListener(accionesDelBotonActualizar)
                .setButtonDismissClickListener(accionesDelBotonCancelar)
                .start();

    }
}
