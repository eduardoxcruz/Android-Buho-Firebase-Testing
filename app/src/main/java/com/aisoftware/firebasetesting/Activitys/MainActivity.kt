package com.aisoftware.firebasetesting.Activitys

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.aisoftware.firebasetesting.Clases.*
import com.aisoftware.firebasetesting.R
import com.github.javiersantos.appupdater.AppUpdaterUtils
import com.github.javiersantos.appupdater.enums.AppUpdaterError
import com.github.javiersantos.appupdater.objects.Update
import com.google.zxing.integration.android.IntentIntegrator.REQUEST_CODE

class MainActivity : AppCompatActivity() {

    private val contexto : Context = this@MainActivity
    private val activity : Activity = this@MainActivity
    private lateinit var permisosDeLaApp : Permisos
    private lateinit var actualizadorDeLaApp : Actualizaciones
    private lateinit var canalesDeNotificaciones: Notificaciones

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        actualizadorDeLaApp = Actualizaciones(contexto, activity)
        canalesDeNotificaciones = Notificaciones(contexto)
        permisosDeLaApp = Permisos(contexto, activity)

        canalesDeNotificaciones.CrearCanalDeNotificacion(
            R.string.CanalDeNotificacion_ID_ParosYReanudaciones,
            R.string.CanalDeNotificacion_Nombre_ParosYReanudaciones,
            R.string.CanalDeNotificacion_Descripcion_ParosYReanudaciones,
            NotificationCompat.PRIORITY_MAX
        )

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

    //Fuente: https://stackoverflow.com/questions/30525784/android-keep-service-running-when-app-is-killed
    private fun MiServicioEstaCorriendo(servicio: Class<*>) : Boolean {

        var miServicioEstaCorriendo : Boolean = false

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