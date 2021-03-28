package com.nombreempresa.tp2_servicios_android;

import android.app.Service;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.provider.Telephony;
import android.util.Log;

public class ServicioSms extends Service implements Runnable{


    public int onStartCommand(Intent intent, int flags, int startId) {
        run();
        return START_STICKY;
    }

            @Override
            public void run() {

                /* Uri del contentProvider al que quiero acceder */
                Uri tablaSms = Uri.parse("content://sms/");


                ContentResolver contenedor = getContentResolver();


                Cursor c = contenedor.query(tablaSms, null, null, null, null);

                /*creo variables para los datos que quiero acceder   */
                String tipo = null;
                String body = null;
                String id = null;
                int i = 0;


                if (c.getCount() > 0 && (i < 6)) {

                    /* el cursor por defecto apunta afuera.   moveToNext se va parando una fila despues del cursor, true sigue.    */
                    while (c.moveToNext()) {

                        if (i == 5) {

                            Log.d("dato", "<<<< Esperando los 9000 milisegundos >>>");
                            try {
                                Thread.sleep(9000);


                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            Log.d("dato", "<<<<< FINALIZO EL HILO >>>>>>");
                            i = 0;
                        } else {

                            i++;

                            //convierto a string lo que recupero en ese indice de columna
                            tipo = c.getString(c.getColumnIndex(Telephony.Sms.TYPE));
                            body = c.getString(c.getColumnIndex(Telephony.Sms.BODY));
                            id = c.getString(c.getColumnIndex(Telephony.Sms._ID));
                            Log.d("dato", "MENSAJE NÚMERO " + i);
                            Log.d("dato", "TIPO: " + tipo);
                            Log.d("dato", "CUERPO DEL MENSAJE: " + body);
                            Log.d("dato", "ID " + id);
                        }
                    }
                }
            }


    @Override
    public IBinder onBind(Intent intent) {
       return null;
    }
    }