package com.aisoftware.firebasetesting.Clases

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

class Permisos {

    fun VerificarQueTodosLosPermisosEstenConcedidos(contexto: Context, activity : Activity) : Boolean{

        var todosLosPermisosConcedidos : Boolean = false

        if(ContextCompat.checkSelfPermission(contexto, Manifest.permission.WRITE_EXTERNAL_STORAGE) +
           ContextCompat.checkSelfPermission(contexto, Manifest.permission.READ_EXTERNAL_STORAGE) +
           ContextCompat.checkSelfPermission(contexto, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

                if (contexto.getPackageManager().canRequestPackageInstalls()){

                    todosLosPermisosConcedidos = true

                }

            }

            else {

                todosLosPermisosConcedidos = true

            }

        }

        return todosLosPermisosConcedidos
    }

    fun SolicitarPermisosRequeridos(contexto : Context, activity: Activity){

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