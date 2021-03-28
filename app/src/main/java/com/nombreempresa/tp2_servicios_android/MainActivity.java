package com.nombreempresa.tp2_servicios_android;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;



public class MainActivity extends AppCompatActivity {
    Intent s;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        acceder();
    }


    @Override
    protected void onResume() {
        super.onResume();
        s = new Intent(this, ServicioSms.class);
        startService(s);

    }


    private void acceder() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.READ_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_SMS}, 2000);
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        stopService(s);

    }
}










