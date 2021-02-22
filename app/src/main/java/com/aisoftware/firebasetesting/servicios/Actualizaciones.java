package com.aisoftware.firebasetesting.servicios;

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
import android.widget.Toast;

import androidx.core.content.FileProvider;

import com.aisoftware.firebasetesting.BuildConfig;
import com.aisoftware.firebasetesting.R;
import com.github.javiersantos.appupdater.AppUpdater;
import com.github.javiersantos.appupdater.AppUpdaterUtils;
import com.github.javiersantos.appupdater.enums.Display;
import com.github.javiersantos.appupdater.enums.UpdateFrom;

import java.io.File;

public class Actualizaciones {

    private String nombreDelArchivoDeActualizacion;
    private String rutaDeDescargaDelArchivoDeActualizacion;
    private Uri UriDelArchivoDeActualizacion;
    private File archivoDeActualizacion;
    private String urlDeLaActualizacion;
    private AppUpdaterUtils updaterUtils;
    private AppUpdater appUpdater;
    private Context contexto;
    private Activity activity;

    public Actualizaciones(Context Contexto, Activity Activity){

        this.nombreDelArchivoDeActualizacion = "buhoUpdate.apk";
        this.rutaDeDescargaDelArchivoDeActualizacion = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/" + nombreDelArchivoDeActualizacion;
        this.UriDelArchivoDeActualizacion = Uri.parse("file://" + rutaDeDescargaDelArchivoDeActualizacion);
        this.archivoDeActualizacion = new File(rutaDeDescargaDelArchivoDeActualizacion);
        this.urlDeLaActualizacion = "https://github.com/eduardoxcruz/FirebaseTesting-Releases/releases/latest/download/app-debug.apk";
        this.contexto = Contexto;
        this.updaterUtils = new AppUpdaterUtils(contexto);
        this.appUpdater = new AppUpdater(contexto);
        this.activity = Activity;

    }

    public void ConsultarActualizacionesNuevasEnElServidor(AppUpdaterUtils.UpdateListener accionDelListener){

        updaterUtils.setUpdateFrom(UpdateFrom.XML)
                .setUpdateXML("https://algoritmosinteligentes.000webhostapp.com/VersionesTQ/androidver.xml")
                .withListener(accionDelListener)
                .start();

    }

    public void MostrarMensajeDeActualizacionDisponible(DialogInterface.OnClickListener accionesDelBotonActualizar, DialogInterface.OnClickListener accionesDelBotonCancelar){

        appUpdater.setDisplay(Display.DIALOG)
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
    public void DescargarActualizacion(){

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

               InstalarActualizacion();

               activity.finish();

            }
        };

        contexto.registerReceiver(AlCompletarLaDescarga, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

    }

    /*
    //Fuente:
    //https://stackoverflow.com/questions/4604239/install-application-programmatically-on-android
    //https://androidwave.com/download-and-install-apk-programmatically/
    //https://inthecheesefactory.com/blog/how-to-share-access-to-file-with-fileprovider-on-android-nougat/
     */
    public void InstalarActualizacion(){

        if(Build.VERSION.SDK_INT == Build.VERSION_CODES.M){

            Intent instalarApp = new Intent(Intent.ACTION_VIEW);
            instalarApp.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            instalarApp.setDataAndType(Uri.fromFile(archivoDeActualizacion), "application/vnd.android.package-archive");
            activity.startActivity(instalarApp);

        }

        else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){

            Uri fileUri = Uri.fromFile(archivoDeActualizacion);
            fileUri = FileProvider.getUriForFile(contexto, BuildConfig.APPLICATION_ID + ".provider", archivoDeActualizacion);

            Intent instalarApp = new Intent(Intent.ACTION_VIEW);
            instalarApp.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            instalarApp.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            instalarApp.putExtra(Intent.EXTRA_NOT_UNKNOWN_SOURCE, true);
            instalarApp.setData(fileUri);
            contexto.startActivity(instalarApp);

        }

    }

    public void DetenerBusquedaDeActualizaciones(){

        appUpdater.stop();
        updaterUtils.stop();

    }
}
