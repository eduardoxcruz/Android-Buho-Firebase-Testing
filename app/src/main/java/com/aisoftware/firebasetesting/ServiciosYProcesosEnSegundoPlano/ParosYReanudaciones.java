package com.aisoftware.firebasetesting.ServiciosYProcesosEnSegundoPlano;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class ParosYReanudaciones extends Service {

    public ParosYReanudaciones() { }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return 0;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}