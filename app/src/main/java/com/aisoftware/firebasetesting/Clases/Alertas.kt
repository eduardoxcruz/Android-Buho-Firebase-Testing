package com.aisoftware.firebasetesting.Clases

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import com.aisoftware.firebasetesting.R

class Alertas {

    fun GenerarAlertaDeError(contexto: Context, mensajeDelDialogo : Int, accionDelClickPositivo : DialogInterface.OnClickListener, accionDelClickNegativo : DialogInterface.OnClickListener){

        AlertDialog.Builder(contexto).
            setIcon(R.drawable.error).
            setTitle(R.string.Error).
            setMessage(mensajeDelDialogo).
            setCancelable(false).
            setPositiveButton(R.string.Aceptar, accionDelClickPositivo).
            setNegativeButton(R.string.Cancelar, accionDelClickNegativo).
            create().
            show()

    }

    fun GenerarAlertaDeExito(contexto: Context, mensajeDelDialogo : Int, accionDelClickPositivo : DialogInterface.OnClickListener, accionDelClickNegativo : DialogInterface.OnClickListener){

        AlertDialog.Builder(contexto).
        setIcon(R.drawable.success).
        setTitle(R.string.Exito).
        setMessage(mensajeDelDialogo).
        setCancelable(false).
        setPositiveButton(R.string.Aceptar, accionDelClickPositivo).
        setNegativeButton(R.string.Cancelar, accionDelClickNegativo).
        create().
        show()

    }

}