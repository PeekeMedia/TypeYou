package com.peekemedia.app.typeyou;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service
{
    final class MyThreadClass implements Runnable
    {
        int service_id;
        MyThreadClass(int service_id)
        {
            this.service_id = service_id;
        }

        @Override
        public void run()
        {
            //create a long running task
            int i =0;
            synchronized (this)
            {
                while(i<10)
                {
                    try
                    {
                        wait(15000);
                        i++;
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
                stopSelf(service_id);
            }
        }
    }


    @Override
    public void onCreate()
    {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        Toast.makeText(this,"Service started...",Toast.LENGTH_LONG).show();
        Thread thread = new Thread(new MyThreadClass(startId));
        thread.start();

        return START_STICKY;



    }

    @Override
    public void onDestroy() {
        Toast.makeText(this,"Service stopped...",Toast.LENGTH_LONG).show();

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        // throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }


}
