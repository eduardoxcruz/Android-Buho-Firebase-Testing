package com.aisoftware.firebasetesting.Activitys

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aisoftware.firebasetesting.Actualizaciones
import com.aisoftware.firebasetesting.Clases.Alertas
import com.aisoftware.firebasetesting.Clases.Permisos
import com.aisoftware.firebasetesting.R
import com.github.javiersantos.appupdater.AppUpdaterUtils
import com.github.javiersantos.appupdater.enums.AppUpdaterError
import com.github.javiersantos.appupdater.objects.Update
import com.google.zxing.integration.android.IntentIntegrator.REQUEST_CODE

class MainActivity : AppCompatActivity() {

    private val permisosDeLaApp = Permisos()
    private val alerta = Alertas()
    private val actualizadorDeLaApp = Actualizaciones()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ConsultaDePermisosYActualizaciones()
    }

    private fun ConsultaDePermisosYActualizaciones(){

        if(permisosDeLaApp.VerificarQueTodosLosPermisosEstenConcedidos(this@MainActivity, this@MainActivity))
        {
            actualizadorDeLaApp.ConsultarActualizacionesNuevasEnElServidor(
                this@MainActivity,
                object : AppUpdaterUtils.UpdateListener
                {
                    override fun onSuccess(update: Update, isUpdateAvailable: Boolean)
                    {
                        if (isUpdateAvailable)
                        {
                            actualizadorDeLaApp.MostrarMensajeDeActualizacionDisponible(
                                this@MainActivity,
                                { dialogInterface, i ->
                                    actualizadorDeLaApp.DescargarActualizacion(this@MainActivity, this@MainActivity)
                                },
                                { dialogInterface, i ->
                                    finish()
                                }
                            )
                        }

                        else
                        {

                        }
                    }

                    override fun onFailed(error: AppUpdaterError)
                    {
                        alerta.GenerarAlertaDeError(
                            this@MainActivity,
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
                this@MainActivity,
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