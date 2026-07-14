package com.example.georeporte;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class BienvenidaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenida);

        Button btnLista = findViewById(R.id.btnIrLista);
        Button btnNuevo = findViewById(R.id.btnIrNuevo);

        // Acción: Ir a la lista de reportes (MainActivity)
        btnLista.setOnClickListener(v -> {
            Intent intent = new Intent(BienvenidaActivity.this, MainActivity.class);
            startActivity(intent);
        });

        // Acción: Ir a crear reporte directamente (NuevoReporteActivity)
        btnNuevo.setOnClickListener(v -> {
            Intent intent = new Intent(BienvenidaActivity.this, NuevoReporteActivity.class);
            startActivity(intent);
        });
    }
}