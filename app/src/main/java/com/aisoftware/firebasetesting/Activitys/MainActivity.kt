package com.aisoftware.firebasetesting.Activitys

import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.aisoftware.firebasetesting.Clases.Actualizaciones
import com.aisoftware.firebasetesting.Clases.Alertas
import com.aisoftware.firebasetesting.Clases.Permisos
import com.aisoftware.firebasetesting.R
import com.github.javiersantos.appupdater.AppUpdaterUtils
import com.github.javiersantos.appupdater.enums.AppUpdaterError
import com.github.javiersantos.appupdater.objects.Update

class MainActivity : AppCompatActivity() {

    private val permisosDeLaApp = Permisos()
    private val alerta = Alertas()
    private val actualizadorDeLaApp = Actualizaciones()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(permisosDeLaApp.VerificarQueTodosLosPermisosEstenConcedidos(this@MainActivity))
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
                                    Toast.makeText(this@MainActivity, "Descargando actualizacion", Toast.LENGTH_SHORT).show()
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

                    permisosDeLaApp.SolicitarPermisosRequeridos(this@MainActivity)

                    Handler().postDelayed({
                        recreate()
                    }, 5000)

                },
                { dialog, wich ->
                    finish()
                }
            )
        }
    }
}