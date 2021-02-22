package com.aisoftware.firebasetesting.clases;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Internet {

    private Context contexto;

    public Internet(Context contexto){

        this.contexto = contexto;

    }

    public boolean ExisteConexionAInternet(){

        boolean existeConexion = false;

        ConnectivityManager connectivityManager = (ConnectivityManager) contexto.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();

        existeConexion = activeNetwork != null && activeNetwork.isConnected();

        return existeConexion;
    }

}
