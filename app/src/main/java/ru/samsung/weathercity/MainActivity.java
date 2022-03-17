package ru.samsung.weathercity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edCity;
    Button button;
    String city;
    WeatherCity weatherCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        edCity = findViewById(R.id.editCity);
        button = findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                city = edCity.getText().toString();
                MyAsyncTask myAsyncTask = new MyAsyncTask();
                myAsyncTask.execute();
                break;
        }
    }

    private class MyAsyncTask extends AsyncTask<Void, Void, String>{
        @Override
        protected String doInBackground(Void... voids) {
            String apiKey = "eceae5bae9142cc79ef4bb4199703b7f";
            String url = "http://api.weatherstack.com/current?access_key=" + apiKey + "&query=" + city;

            try {
                URL url1 = new URL(url);
                URLConnection connection = url1.openConnection();
                InputStream inputStream = connection.getInputStream();
                Scanner in = new Scanner(inputStream);

                String result = "";
                while (in.hasNext()) {
                    result += in.nextLine();
                }
                in.close();
                inputStream.close();
                Log.d("My", result);
                weatherCity = new WeatherCity(result);
                Log.d("My", weatherCity.toString());
            } catch (Exception e){
                Log.d("My", "Не удалось получить данные");
                e.printStackTrace();
            }
            return null;
        }
    }
}