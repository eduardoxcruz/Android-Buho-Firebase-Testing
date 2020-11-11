package com.aisoftware.firebasetesting;

import android.content.Context;
import android.content.DialogInterface;

import com.github.javiersantos.appupdater.AppUpdater;
import com.github.javiersantos.appupdater.enums.Display;
import com.github.javiersantos.appupdater.enums.UpdateFrom;

public class Actualizaciones {

    private Context contexto;
    private AppUpdater actualizador = new AppUpdater(contexto);

    public Actualizaciones(Context contexto){
        this.contexto = contexto;
    }

    private void ConsultarActualizacionesNuevasEnElServidor(){

        actualizador.setDisplay(Display.DIALOG).
                setTitleOnUpdateAvailable(R.string.HayUnaActualizacionDisponible).
                setIcon(R.drawable.success).
                setButtonUpdate(R.string.Actualizar).
                setCancelable(false).
                setButtonDismiss(null).
                setButtonDoNotShowAgain(null).
                setUpdateFrom(UpdateFrom.XML).
                setUpdateXML("https://algoritmosinteligentes.000webhostapp.com/VersionesTQ/androidver.xml").
                setButtonUpdateClickListener(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
    }
}