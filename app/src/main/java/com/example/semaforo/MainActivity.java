package com.example.semaforo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnHilo;
    View v_circulo;

    private int colorActual = 0;

    private final int[] semaforo = {R.drawable.circular_rojo, R.drawable.circular_amarillo, R.drawable.circular_verde};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        v_circulo = findViewById(R.id.v_circuloRojo);
        btnHilo = findViewById(R.id.btnActivar);

        btnHilo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true)
                        {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    v_circulo.setBackgroundResource(semaforo[colorActual]);
                                    colorActual = (colorActual + 1) % semaforo.length;
                                }
                            });
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                });
                thread.start();
            }
        });
    }
}