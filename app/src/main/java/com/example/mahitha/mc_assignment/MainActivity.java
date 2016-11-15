package com.example.mahitha.mc_assignment;

import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.OrientationEventListener;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {
    Button download_button;
    String textdata;
    String str = new String("https://www.iiitd.ac.in/about");
    TextView textView;
    OrientationEventListener mOrientationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        download_button = (Button)findViewById(R.id.download);
        textView = (TextView) findViewById(R.id.textView);


        download_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DownloadFromURL().execute(str);
            }
        });
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    class DownloadFromURL extends AsyncTask<String,String,String>{
        @Override
        protected String doInBackground(String... strings) {
            int count;
            try {

                URL url = new URL(strings[0]);
                URLConnection conection = url.openConnection();
                conection.connect();

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(url.openStream()));

                String inputLine;
                textdata = in.readLine();
                while ((inputLine = in.readLine()) != null)
                    Log.d("dataoutput",inputLine);
                in.close();

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            textView.setText(textdata);
        }
    }
}

