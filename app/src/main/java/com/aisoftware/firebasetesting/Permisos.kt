package com.aisoftware.firebasetesting

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.ContextCompat
import com.google.zxing.integration.android.IntentIntegrator.REQUEST_CODE

class Permisos {

    fun VerificarQueTodosLosPermisosEstenConcedidos(contexto: Context) : Boolean{

        var todosLosPermisosConcedidos : Boolean = false

        if(ContextCompat.checkSelfPermission(contexto, Manifest.permission.WRITE_EXTERNAL_STORAGE) +
           ContextCompat.checkSelfPermission(contexto, Manifest.permission.READ_EXTERNAL_STORAGE) +
           ContextCompat.checkSelfPermission(contexto, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){

            todosLosPermisosConcedidos = true
        }

        return todosLosPermisosConcedidos
    }
}