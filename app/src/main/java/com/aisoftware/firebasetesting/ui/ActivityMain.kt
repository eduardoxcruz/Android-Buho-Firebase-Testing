package com.aisoftware.firebasetesting.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityManager
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.aisoftware.firebasetesting.R
import com.aisoftware.firebasetesting.clases.*
import com.aisoftware.firebasetesting.servicios.Actualizaciones
import com.github.javiersantos.appupdater.AppUpdaterUtils
import com.github.javiersantos.appupdater.enums.AppUpdaterError
import com.github.javiersantos.appupdater.objects.Update
import com.google.zxing.integration.android.IntentIntegrator.REQUEST_CODE

class ActivityMain : AppCompatActivity() {

    private val contexto: Context = this@ActivityMain
    private val activity: Activity = this@ActivityMain
    private lateinit var permisosDeLaApp: Permisos
    private lateinit var actualizadorDeLaApp: Actualizaciones

    @SuppressLint("InlinedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        actualizadorDeLaApp =
            Actualizaciones(
                contexto,
                activity
            )

        Notificaciones(contexto).crearCanalDeNotificacion(
            R.string.CanalDeNotificacion_ID_NotificacionesPush,
            R.string.CanalDeNotificacion_Nombre_NotificacionesPush,
            R.string.CanalDeNotificacion_Descripcion_NotificacionesPush,
            NotificationManager.IMPORTANCE_MAX
        )

        permisosDeLaApp = Permisos(contexto, activity)

    }

    override fun onStart() {
        super.onStart()
    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun onResume() {
        super.onResume()

        if (MonitorDeRed(contexto).existeConexionAInternet()) {

            if (!permisosDeLaApp.verificarQueTodosLosPermisosEstenConcedidos()) {

                Alertas(contexto)
                    .generarAlertaDeAviso()
                    .setMessage(R.string.ParaQueLaAplicacionFuncioneCorrecamenteSeDebePermitirCiertosAccesosAlTelefono)
                    .setPositiveButton(
                        R.string.Entendido
                    )
                    { dialog, wich ->
                        permisosDeLaApp.solicitarPermisos()
                    }
                    .setNegativeButton(R.string.Cancelar)
                    { dialog, wich ->
                        finish()
                    }
                    .create()
                    .show()

            } else {
                actualizadorDeLaApp.buscarActualizacionesNuevas(
                    object : AppUpdaterUtils.UpdateListener {
                        override fun onSuccess(update: Update, isUpdateAvailable: Boolean) {
                            if (isUpdateAvailable) {
                                actualizadorDeLaApp.mostrarMensajeDeActualizacionDisponible(
                                    { dialogInterface, i ->
                                        actualizadorDeLaApp.descargarActualizacion()
                                    },
                                    { dialogInterface, i ->
                                        finish()
                                    }
                                )
                            } else {
                                actualizadorDeLaApp.detenerBusquedaDeActualizaciones()
                                val irAContenedorFinal =
                                    Intent(contexto, ActivityPrincipalNavBar::class.java)
                                startActivity(irAContenedorFinal)
                                finish()
                            }
                        }

                        override fun onFailed(error: AppUpdaterError) {

                            Alertas(contexto)
                                .generarAlertaDeError()
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

        } else {

            Alertas(contexto)
                .generarAlertaDeError()
                .setMessage(R.string.ParaPoderUsarLaAplicacionSeRequiereUnaConexionAInternet)
                .setPositiveButton(
                    R.string.VolverAIntentarlo
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
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            REQUEST_CODE -> {
                recreate()
            }
        }

    }

    //Fuente: https://stackoverflow.com/questions/30525784/android-keep-service-running-when-app-is-killed
    private fun miServicioEstaCorriendo(servicio: Class<*>): Boolean {

        var miServicioEstaCorriendo: Boolean = false

        val activityManager = this.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

        for (serviciosCorriendo in activityManager.getRunningServices(Int.MAX_VALUE)) {

            if (servicio.name == serviciosCorriendo.service.className) {
                Toast.makeText(contexto, "Servicio Corriendo", Toast.LENGTH_SHORT).show()
                miServicioEstaCorriendo = true
            }

        }

        return miServicioEstaCorriendo
    }

}