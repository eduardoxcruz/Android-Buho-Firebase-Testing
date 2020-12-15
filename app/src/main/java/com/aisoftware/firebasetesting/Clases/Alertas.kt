package com.aisoftware.firebasetesting.Clases

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import com.aisoftware.firebasetesting.R

class Alertas (Contexto : Context){

    private val contexto = Contexto
    private val alertDialog = AlertDialog.Builder(contexto).setCancelable(false)

    fun GenerarAlertaDeError(mensajeDelDialogo : Int, accionDelClickPositivo : DialogInterface.OnClickListener, accionDelClickNegativo : DialogInterface.OnClickListener){

        alertDialog.setIcon(R.drawable.error).
        setTitle(R.string.Error).
        setMessage(mensajeDelDialogo).
        setPositiveButton(R.string.Aceptar, accionDelClickPositivo).
        setNegativeButton(R.string.Cancelar, accionDelClickNegativo).
        create().
        show()

    }

    fun GenerarAlertaDeExito(mensajeDelDialogo : Int, accionDelClickPositivo : DialogInterface.OnClickListener, accionDelClickNegativo : DialogInterface.OnClickListener){

        alertDialog.setIcon(R.drawable.success).
        setTitle(R.string.Exito).
        setMessage(mensajeDelDialogo).
        setPositiveButton(R.string.Aceptar, accionDelClickPositivo).
        setNegativeButton(R.string.Cancelar, accionDelClickNegativo).
        create().
        show()

    }

    fun GenerarAlertaDeAviso(mensajeDelDialogo : Int, accionDelClickPositivo : DialogInterface.OnClickListener, accionDelClickNegativo : DialogInterface.OnClickListener){

        alertDialog.setIcon(R.drawable.alert).
        setTitle(R.string.Aviso).
        setMessage(mensajeDelDialogo).
        setPositiveButton(R.string.Aceptar, accionDelClickPositivo).
        setNegativeButton(R.string.Cancelar, accionDelClickNegativo).
        create().
        show()

    }
}