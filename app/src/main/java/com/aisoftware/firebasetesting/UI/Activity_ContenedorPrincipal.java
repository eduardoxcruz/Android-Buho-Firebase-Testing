package com.aisoftware.firebasetesting.UI;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.aisoftware.firebasetesting.R;

public class Activity_ContenedorPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contenedor_principal);

        NavHostFragment fragmentHostDeNavegacion = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentHostDeNavegacion);
        if (fragmentHostDeNavegacion != null) {
            NavController controladorDeNavegacion = fragmentHostDeNavegacion.getNavController();
        }

    }
}