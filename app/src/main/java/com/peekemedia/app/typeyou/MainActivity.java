package com.peekemedia.app.typeyou;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.BufferedReader;

public class MainActivity extends AppCompatActivity {
    TextView textview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //added by joe
        textview = (TextView)findViewById(R.id.textView4);
        textview.setVisibility(View.GONE);
    }

    public void startService(View view) {
        Intent intent = new Intent(this,MyService.class);
        startService(intent);
    }

    public void stopService(View view) {
        Intent intent = new Intent(this,MyService.class);
        stopService(intent);
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    //write to internal storage
    public void writeMessage(View view)
    {
        String Message = "Joseph Burton";
        String file_name = "filename";

        try {
            FileOutputStream fileOutputStream = openFileOutput(file_name,MODE_PRIVATE);
            fileOutputStream.write(Message.getBytes());
            fileOutputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readMessage(View view)
    {
        try
        {
            String Message;
            String file_name = "filename";
            FileInputStream fileInputStream = openFileInput(file_name);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();
            while ((Message = bufferedReader.readLine()) != null)
            {
                stringBuffer.append(Message + "\n");

            }
            textview.setText(stringBuffer.toString());
            textview.setVisibility(View.VISIBLE);


        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}

