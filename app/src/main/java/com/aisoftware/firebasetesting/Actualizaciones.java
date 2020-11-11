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

    private AppUpdater actualizador;
    private AppUpdaterUtils consultadorDeActualizaciones;
    private boolean hayActualizacionDisponible;

    public boolean getHayActualizacionDisponible() {
        return hayActualizacionDisponible;
    }

    public void setHayActualizacionDisponible(boolean value) {
        this.hayActualizacionDisponible = value;
    }

    public Actualizaciones(){
        this.hayActualizacionDisponible = true;
    }

    private void ConsultarActualizacionesNuevasEnElServidor(){

        consultadorDeActualizaciones.setUpdateFrom(UpdateFrom.XML).
                setUpdateXML("https://algoritmosinteligentes.000webhostapp.com/VersionesTQ/androidver.xml").
                withListener(new AppUpdaterUtils.UpdateListener() {
                    @Override
                    public void onSuccess(Update update, Boolean isUpdateAvailable) {

                        if(isUpdateAvailable == true){

                        }

                        else{
                            setHayActualizacionDisponible(false);
                        }
                    }

                    @Override
                    public void onFailed(AppUpdaterError error) {

                    }
                });
    }

    private void MostrarMensajeDeActualizacionDisponible(){

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