package com.example.semaforo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnHilo;
    View v_circuloRojo1;
    View v_circuloAmarillo1;
    View v_circuloVerde1;
    View v_circuloRojo2;
    View v_circuloAmarillo2;
    View v_circuloVerde2;

    private int estadoActual = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        v_circuloRojo1 = findViewById(R.id.v_circuloRojo);
        v_circuloAmarillo1 = findViewById(R.id.v_circuloAmarillo);
        v_circuloVerde1 = findViewById(R.id.v_circuloVerde);
        v_circuloRojo2 = findViewById(R.id.v_circuloRojo2);
        v_circuloAmarillo2 = findViewById(R.id.v_circuloAmarillo2);
        v_circuloVerde2 = findViewById(R.id.v_circuloVerde2);

        btnHilo = findViewById(R.id.btnActivar);

        btnHilo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnHilo.setEnabled(false);

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    cambiarColoresSemaforos();
                                    estadoActual = (estadoActual + 1) % 3;
                                }
                            });
                            try {
                                Thread.sleep(5000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
                thread.start();
            }
        });
    }

    private void cambiarColoresSemaforos() {
        v_circuloRojo1.setBackgroundResource(R.drawable.circular_blanco);
        v_circuloAmarillo1.setBackgroundResource(R.drawable.circular_blanco);
        v_circuloVerde1.setBackgroundResource(R.drawable.circular_blanco);
        v_circuloRojo2.setBackgroundResource(R.drawable.circular_blanco);
        v_circuloAmarillo2.setBackgroundResource(R.drawable.circular_blanco);
        v_circuloVerde2.setBackgroundResource(R.drawable.circular_blanco);

        switch (estadoActual) {
            case 0:
                v_circuloRojo1.setBackgroundResource(R.drawable.circular_rojo);
                v_circuloVerde2.setBackgroundResource(R.drawable.circular_verde);
                break;
            case 1:
                v_circuloAmarillo1.setBackgroundResource(R.drawable.circular_amarillo);
                v_circuloAmarillo2.setBackgroundResource(R.drawable.circular_amarillo);
                break;
            case 2:
                v_circuloVerde1.setBackgroundResource(R.drawable.circular_verde);
                v_circuloRojo2.setBackgroundResource(R.drawable.circular_rojo);
                break;
        }
    }
}


