package com.aisoftware.firebasetesting;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.widget.Toast;

import com.github.javiersantos.appupdater.AppUpdater;
import com.github.javiersantos.appupdater.AppUpdaterUtils;
import com.github.javiersantos.appupdater.enums.Display;
import com.github.javiersantos.appupdater.enums.UpdateFrom;

import java.io.File;

public class Actualizaciones {

    private String nombreDelArchivoDeActualizacion = "buhoUpdate.apk";
    private String rutaDeDescargaDelArchivoDeActualizacion = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/" + nombreDelArchivoDeActualizacion;
    private Uri UriDelArchivoDeActualizacion = Uri.parse("file://" + rutaDeDescargaDelArchivoDeActualizacion);
    private File archivoDeActualizacion = new File(rutaDeDescargaDelArchivoDeActualizacion);
    private String urlDeLaActualizacion = "https://github.com/eduardoxcruz/Publish/releases/latest/download/app-debug.apk"; //"https://github.com/eduardoxcruz/Publish/releases/download/1.0.0.1/app-debug.apk";

    public void ConsultarActualizacionesNuevasEnElServidor(Context contexto, AppUpdaterUtils.UpdateListener accionDelListener){

        new AppUpdaterUtils(contexto)
                .setUpdateFrom(UpdateFrom.XML)
                .setUpdateXML("https://algoritmosinteligentes.000webhostapp.com/VersionesTQ/androidver.xml")
                .withListener(accionDelListener)
                .start();

    }

    public void MostrarMensajeDeActualizacionDisponible(Context contexto, DialogInterface.OnClickListener accionesDelBotonActualizar, DialogInterface.OnClickListener accionesDelBotonCancelar){

        new AppUpdater(contexto)
                .setDisplay(Display.DIALOG)
                .setTitleOnUpdateAvailable(R.string.HayUnaActualizacionDisponible)
                .setIcon(R.drawable.success)
                .setCancelable(false)
                .setButtonUpdate(R.string.Actualizar)
                .setButtonDismiss(R.string.Cancelar)
                .setButtonDoNotShowAgain(null)
                .setUpdateFrom(UpdateFrom.XML)
                .setUpdateXML("https://algoritmosinteligentes.000webhostapp.com/VersionesTQ/androidver.xml")
                .setButtonUpdateClickListener(accionesDelBotonActualizar)
                .setButtonDismissClickListener(accionesDelBotonCancelar)
                .start();

    }


    //Fuente: https://stackoverflow.com/questions/4967669/android-install-apk-programmatically
    public void DescargarActualizacion(final Context contexto, Activity activity){

        //TODO: First I wanted to store my update .apk file on internal storage for my app but apparently android does not allow you to open and install
        //aplication with existing package from there. So for me, alternative solution is Download directory in external storage. If there is better
        //solution, please inform us in comment

        if(archivoDeActualizacion.exists()){
            archivoDeActualizacion.delete();
        }

        DownloadManager.Request solicitudDeDescarga = new DownloadManager.Request(Uri.parse(urlDeLaActualizacion));
        solicitudDeDescarga.setTitle("Descargando actualizacion de Buho");
        solicitudDeDescarga.setDestinationUri(UriDelArchivoDeActualizacion);

        // get download service and enqueue file
        DownloadManager managerDeDescarga = (DownloadManager) contexto.getSystemService(Context.DOWNLOAD_SERVICE);
        managerDeDescarga.enqueue(solicitudDeDescarga);

        Toast.makeText(contexto, R.string.DescargaIniciada, Toast.LENGTH_LONG).show();

        //set BroadcastReceiver to install app when .apk is downloaded
        BroadcastReceiver AlCompletarLaDescarga = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                Toast.makeText(contexto, R.string.DescargaFinalizada, Toast.LENGTH_LONG).show();

               contexto.unregisterReceiver(this);
            }
        };

        contexto.registerReceiver(AlCompletarLaDescarga, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

    }

    public void InstalarActualizacion(Activity activity){

        if(Build.VERSION.SDK_INT <= Build.VERSION_CODES.M){

            Intent instalarApp = new Intent(Intent.ACTION_VIEW);
            instalarApp.setDataAndType(UriDelArchivoDeActualizacion, "application/vnd.android.package-archive");
            activity.startActivity(instalarApp);

        }

    }
}
