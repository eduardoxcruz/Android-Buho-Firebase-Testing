package com.aisoftware.firebasetesting.Activitys

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.aisoftware.firebasetesting.Clases.Alertas
import com.aisoftware.firebasetesting.Clases.Permisos
import com.aisoftware.firebasetesting.R

class MainActivity : AppCompatActivity() {

    private val permisosDeLaApp = Permisos()
    private val alerta = Alertas()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(permisosDeLaApp.VerificarQueTodosLosPermisosEstenConcedidos(this@MainActivity))

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