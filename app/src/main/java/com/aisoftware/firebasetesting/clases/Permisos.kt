package com.aisoftware.firebasetesting.clases

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.ContextCompat
import com.google.zxing.integration.android.IntentIntegrator.REQUEST_CODE

class Permisos(Contexto : Context, Activity : Activity) {

    private val contexto = Contexto
    private val activity = Activity

    fun VerificarQueTodosLosPermisosEstenConcedidos() : Boolean{

        var todosLosPermisosConcedidos : Boolean = false
        var concedido_PermisosDelTelefono : Boolean = false
        var concedido_PermisoDeInstalacionDePaquetes : Boolean = false

        if(ContextCompat.checkSelfPermission(contexto, Manifest.permission.WRITE_EXTERNAL_STORAGE) +
           ContextCompat.checkSelfPermission(contexto, Manifest.permission.READ_EXTERNAL_STORAGE) +
           ContextCompat.checkSelfPermission(contexto, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED)
        {
            concedido_PermisosDelTelefono = true
        }

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {

            if (contexto.getPackageManager().canRequestPackageInstalls()) concedido_PermisoDeInstalacionDePaquetes = true

        }

        else concedido_PermisoDeInstalacionDePaquetes = true


        if(concedido_PermisosDelTelefono && concedido_PermisoDeInstalacionDePaquetes == true)
        {
            todosLosPermisosConcedidos = true
        }

        return todosLosPermisosConcedidos
    }

    fun SolicitarPermisosRequeridos(){

        requestPermissions(
            activity,
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA),
            REQUEST_CODE)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            if (!contexto.getPackageManager().canRequestPackageInstalls()){

                activity.startActivity(Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, Uri.parse("package:" + "com.aisoftware.firebasetesting")));

            }

        }

    }
}