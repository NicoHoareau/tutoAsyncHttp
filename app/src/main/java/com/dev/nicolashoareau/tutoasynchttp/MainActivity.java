package com.dev.nicolashoareau.tutoasynchttp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b = (Button)findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                AsyncHTTP task = new AsyncHTTP(MainActivity.this);//appel de la classe AsyncHttp
                task.execute("https://openclassrooms.com/");//on passe l'url dans la méthode d'éxécution
            }
        });
    }
}
