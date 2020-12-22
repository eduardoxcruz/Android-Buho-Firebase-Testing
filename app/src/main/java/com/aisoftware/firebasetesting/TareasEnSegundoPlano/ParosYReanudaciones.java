package com.aisoftware.firebasetesting.TareasEnSegundoPlano;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class ParosYReanudaciones extends Worker {

    public ParosYReanudaciones(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        return null;
    }

}
