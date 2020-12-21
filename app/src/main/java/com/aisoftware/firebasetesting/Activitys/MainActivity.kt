package com.aisoftware.firebasetesting.Activitys

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aisoftware.firebasetesting.Clases.*
import com.aisoftware.firebasetesting.R
import com.github.javiersantos.appupdater.AppUpdaterUtils
import com.github.javiersantos.appupdater.enums.AppUpdaterError
import com.github.javiersantos.appupdater.objects.Update
import com.google.zxing.integration.android.IntentIntegrator.REQUEST_CODE

class MainActivity : AppCompatActivity() {

    private val permisosDeLaApp = Permisos()
    private lateinit var actualizadorDeLaApp : Actualizaciones
    private lateinit var canalesDeNotificaciones: Notificaciones
    private lateinit var contexto : Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        contexto = this@MainActivity
        actualizadorDeLaApp = Actualizaciones(contexto, this@MainActivity)
        canalesDeNotificaciones = Notificaciones(contexto)

    }

    override fun onStart() {
        super.onStart()
    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun ConsultaDePermisosYActualizaciones(){

        if(permisosDeLaApp.VerificarQueTodosLosPermisosEstenConcedidos(this@MainActivity, this@MainActivity))
        {
            actualizadorDeLaApp.ConsultarActualizacionesNuevasEnElServidor(
                object : AppUpdaterUtils.UpdateListener
                {
                    override fun onSuccess(update: Update, isUpdateAvailable: Boolean)
                    {
                        if (isUpdateAvailable)
                        {
                            actualizadorDeLaApp.MostrarMensajeDeActualizacionDisponible(
                                { dialogInterface, i ->
                                    actualizadorDeLaApp.DescargarActualizacion()
                                },
                                { dialogInterface, i ->
                                    finish()
                                }
                            )
                        }

                        else
                        {
                            actualizadorDeLaApp.DetenerBusquedaDeActualizaciones()
                        }
                    }

                    override fun onFailed(error: AppUpdaterError)
                    {
                        alerta.GenerarAlertaDeError(
                            R.string.HuboUnErrorAlConectarConElServidorDeActualizaciones,
                            { dialog, wich ->
                                dialog.dismiss()
                            },
                            { dialog, wich ->
                                dialog.dismiss()
                            }
                        )
                    }
                }
            )
        }

        else
        {
            alerta.GenerarAlertaDeAviso(
                R.string.ParaQueLaAplicacionFuncioneCorrecamenteSeDebePermitirCiertosAccesosAlTelefono,
                { dialog, wich ->

                    permisosDeLaApp.SolicitarPermisosRequeridos(this@MainActivity, this@MainActivity)

                },
                { dialog, wich ->
                    finish()
                }
            )
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {

        when (requestCode) {

            REQUEST_CODE -> {

                recreate()

            }

        }

    }

}