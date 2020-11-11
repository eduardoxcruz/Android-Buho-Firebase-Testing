package com.aisoftware.firebasetesting

import android.content.Context
import android.content.DialogInterface
import com.github.javiersantos.appupdater.AppUpdater
import com.github.javiersantos.appupdater.AppUpdaterUtils
import com.github.javiersantos.appupdater.AppUpdaterUtils.UpdateListener
import com.github.javiersantos.appupdater.enums.AppUpdaterError
import com.github.javiersantos.appupdater.enums.Display
import com.github.javiersantos.appupdater.enums.UpdateFrom
import com.github.javiersantos.appupdater.objects.Update

class Actualizaciones {

    fun ConsultarActualizacionesNuevasEnElServidor(contexto: Context?) {

        val consultadorDeActualizaciones = AppUpdaterUtils(contexto)
            .setUpdateFrom(UpdateFrom.XML)
            .setUpdateXML("https://algoritmosinteligentes.000webhostapp.com/VersionesTQ/androidver.xml")
            .withListener(object : UpdateListener {
                override fun onSuccess(update: Update, isUpdateAvailable: Boolean) {

                    if (isUpdateAvailable)
                    {

                    }

                    else
                    {

                    }
                }

                override fun onFailed(error: AppUpdaterError) {}
            })

    }

    fun MostrarMensajeDeActualizacionDisponible(contexto: Context?) {

        val actualizador: AppUpdater = AppUpdater(contexto)
            .setDisplay(Display.DIALOG)
            .setTitleOnUpdateAvailable(R.string.HayUnaActualizacionDisponible)
            .setIcon(R.drawable.success)
            .setButtonUpdate(R.string.Actualizar)
            .setCancelable(false)
            //.setButtonDismiss(null)
            //.setButtonDoNotShowAgain(null)
            .setUpdateFrom(UpdateFrom.XML)
            .setUpdateXML("https://algoritmosinteligentes.000webhostapp.com/VersionesTQ/androidver.xml")
            .setButtonUpdateClickListener(DialogInterface.OnClickListener { dialogInterface, i -> })

    }
}