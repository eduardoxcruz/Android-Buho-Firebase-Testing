package com.aisoftware.firebasetesting

import android.content.Context
import android.content.DialogInterface
import com.github.javiersantos.appupdater.AppUpdater
import com.github.javiersantos.appupdater.AppUpdaterUtils
import com.github.javiersantos.appupdater.AppUpdaterUtils.UpdateListener
import com.github.javiersantos.appupdater.enums.Display
import com.github.javiersantos.appupdater.enums.UpdateFrom

class Actualizaciones {

    fun ConsultarActualizacionesNuevasEnElServidor(contexto: Context?, accionesDelListener : UpdateListener) {

        val consultadorDeActualizaciones = AppUpdaterUtils(contexto)
            .setUpdateFrom(UpdateFrom.XML)
            .setUpdateXML("https://algoritmosinteligentes.000webhostapp.com/VersionesTQ/androidver.xml")
            .withListener(accionesDelListener)
            .start()

    }

    fun MostrarMensajeDeActualizacionDisponible(contexto: Context?, accionesDelBotonActualizar : DialogInterface.OnClickListener, accionesDelBotonCancelar : DialogInterface.OnClickListener) {

        val actualizador = AppUpdater(contexto)
            .setDisplay(Display.DIALOG)
            .setTitleOnUpdateAvailable(R.string.HayUnaActualizacionDisponible)
            .setIcon(R.drawable.success)
            .setButtonUpdate(R.string.Actualizar)
            .setCancelable(false)
            .setButtonDismiss("") //En java para poder desactivar estos botones se tiene que usar "null" (sin comillas)
            .setButtonDoNotShowAgain("") //pero en Kotlin no se puede, esto para evitar NullPointerExceptions
            .setUpdateFrom(UpdateFrom.XML)
            .setUpdateXML("https://algoritmosinteligentes.000webhostapp.com/VersionesTQ/androidver.xml")
            .setButtonUpdateClickListener(accionesDelBotonActualizar)
            .setButtonDismissClickListener(accionesDelBotonCancelar)
            .start()

    }
}