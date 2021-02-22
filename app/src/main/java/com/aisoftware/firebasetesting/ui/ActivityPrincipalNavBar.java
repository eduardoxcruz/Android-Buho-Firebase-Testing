package com.aisoftware.firebasetesting.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.aisoftware.firebasetesting.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ActivityPrincipalNavBar extends AppCompatActivity {

    private BottomNavigationView barraDeNavegacionInferior;
    private AppBarConfiguration configuracionDeLaBarraDeLaApp;
    private NavController controladorDeNavegacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_principal_nav_bar);

        barraDeNavegacionInferior = findViewById(R.id.barraDeNavegacionInferior);
        controladorDeNavegacion = Navigation.findNavController(this, R.id.fragmentHostDeNavegacion);
        configuracionDeLaBarraDeLaApp = new AppBarConfiguration.Builder(
                R.id.navigation_home,
                R.id.navigation_notifications,
                R.id.navigation_desempeno,
                R.id.navigation_empresa,
                R.id.navigation_usuario)
                .build();

        NavigationUI.setupActionBarWithNavController(this, controladorDeNavegacion, configuracionDeLaBarraDeLaApp);
        NavigationUI.setupWithNavController(barraDeNavegacionInferior, controladorDeNavegacion);
    }

}