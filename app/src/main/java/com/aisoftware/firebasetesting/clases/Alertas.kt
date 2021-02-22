package com.aisoftware.firebasetesting.clases

import android.app.AlertDialog
import android.content.Context
import com.aisoftware.firebasetesting.R

class Alertas(Contexto : Context) : AlertDialog.Builder(Contexto) {

    private val alertDialog = AlertDialog.Builder(Contexto).setCancelable(false)

    fun GenerarAlertaDeError() : AlertDialog.Builder{

        alertDialog.setIcon(R.drawable.error).
        setTitle(R.string.Error)

        return alertDialog
    }

    fun GenerarAlertaDeExito() : AlertDialog.Builder{

        alertDialog.setIcon(R.drawable.success).
        setTitle(R.string.Exito)

        return alertDialog
    }

    fun GenerarAlertaDeAviso() : AlertDialog.Builder{

        alertDialog.setIcon(R.drawable.alert).
        setTitle(R.string.Aviso)

        return alertDialog
    }

}