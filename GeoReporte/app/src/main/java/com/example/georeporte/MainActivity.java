package com.example.georeporte;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

/*
* ==============================================================
* PROYECTO: Wachaout
* MIEMBROS DEL EQUIPO:
* 1. [Luis Fabrizzio Ramirez Romero]
* 2. [David Benitez Munoz]
* 3. [Diego Rosales Benitez]
* ==============================================================
*/

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ReporteAdapter adapter;
    List<Reporte> listaReportes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerViewReportes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = findViewById(R.id.fabAgregar);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, NuevoReporteActivity.class);
            startActivity(intent);
        });

        cargarDatos();
    }

    @Override
    protected void onResume() {
        super.onResume();
        cargarDatos(); // Recargar la lista al volver
    }

    private void cargarDatos() {
        listaReportes = new ArrayList<>();
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this);
        SQLiteDatabase db = admin.getReadableDatabase();

        Cursor fila = db.rawQuery("SELECT titulo, descripcion, latitud, longitud, foto_uri, fecha FROM reportes ORDER BY id DESC", null);

        while (fila.moveToNext()) {
            listaReportes.add(new Reporte(
                    fila.getString(0),
                    fila.getString(1),
                    fila.getDouble(2),
                    fila.getDouble(3),
                    fila.getString(4),
                    fila.getString(5)
            ));
        }
        fila.close();

        adapter = new ReporteAdapter(listaReportes);
        recyclerView.setAdapter(adapter);
    }
}