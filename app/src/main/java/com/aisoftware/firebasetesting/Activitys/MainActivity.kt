package com.aisoftware.firebasetesting.Activitys

import android.app.Activity
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

    private lateinit var permisosDeLaApp : Permisos
    private lateinit var actualizadorDeLaApp : Actualizaciones
    private lateinit var canalesDeNotificaciones: Notificaciones
    private lateinit var contexto : Context
    private lateinit var activity: Activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        contexto = this@MainActivity
        activity = this@MainActivity
        actualizadorDeLaApp = Actualizaciones(contexto, activity)
        canalesDeNotificaciones = Notificaciones(contexto)
        permisosDeLaApp = Permisos(contexto, activity)

    }

    override fun onStart() {
        super.onStart()

        if (Internet(contexto).ExisteConexionAInternet())
        {

            if(!permisosDeLaApp.VerificarQueTodosLosPermisosEstenConcedidos()){

                Alertas(contexto)
                    .GenerarAlertaDeAviso()
                    .setMessage(R.string.ParaQueLaAplicacionFuncioneCorrecamenteSeDebePermitirCiertosAccesosAlTelefono)
                    .setPositiveButton(R.string.Entendido
                    )
                    { dialog, wich ->
                        permisosDeLaApp.SolicitarPermisosRequeridos()
                    }
                    .setNegativeButton(R.string.Cancelar)
                    { dialog, wich ->
                        finish()
                    }
                    .create()
                    .show()

            }

        }

        else
        {

            Alertas(contexto)
                .GenerarAlertaDeError()
                .setMessage(R.string.ParaPoderUsarLaAplicacionSeRequiereUnaConexionAInternet)
                .setPositiveButton(R.string.VolverAIntentarlo
                )
                { dialog, wich ->
                    recreate()
                }
                .setNegativeButton(R.string.Cancelar)
                { dialog, wich ->
                    finish()
                }
                .create()
                .show()

        }

    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun onResume() {
        super.onResume()

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

                        Alertas(contexto)
                            .GenerarAlertaDeError()
                            .setMessage(R.string.HuboUnErrorAlConectarConElServidorDeActualizaciones)
                            .setPositiveButton(R.string.VolverAIntentarlo)
                            { dialog, wich ->
                                recreate()
                            }
                            .setNegativeButton(R.string.Cancelar)
                            { dialog, wich ->
                                dialog.dismiss()
                            }
                            .create()
                            .show()

                    }

                }
        )

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

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {

        when (requestCode) {
            REQUEST_CODE -> {
                recreate()
            }
        }

    }

}