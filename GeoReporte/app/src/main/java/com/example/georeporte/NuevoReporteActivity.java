package com.example.georeporte;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NuevoReporteActivity extends AppCompatActivity implements LocationListener {

    EditText etTitulo, etDescripcion;
    TextView tvUbicacion;
    ImageView imgPreview;
    Button btnFoto, btnGuardar;

    String currentPhotoPath;
    double latitud = 0.0, longitud = 0.0;
    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_reporte);

        etTitulo = findViewById(R.id.etTitulo);
        etDescripcion = findViewById(R.id.etDescripcion);
        tvUbicacion = findViewById(R.id.tvUbicacion);
        imgPreview = findViewById(R.id.imgPreview);
        btnFoto = findViewById(R.id.btnTomarFoto);
        btnGuardar = findViewById(R.id.btnGuardar);

        // Inicializar GPS
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        obtenerUbicacion();

        btnFoto.setOnClickListener(v -> dispatchTakePictureIntent());

        btnGuardar.setOnClickListener(v -> guardarEnBD());
    }

    private void obtenerUbicacion() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CAMERA}, 1);
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        // Intentar obtener la última conocida para agilizar
        Location lastLoc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if(lastLoc != null) onLocationChanged(lastLoc);
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        latitud = location.getLatitude();
        longitud = location.getLongitude();
        tvUbicacion.setText("Lat: " + latitud + ", Lon: " + longitud);
    }

    // Lógica para tomar foto y crear archivo
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) { }

            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.georeporte.fileprovider", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, 101);
            }
        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && resultCode == RESULT_OK) {
            Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath);
            imgPreview.setImageBitmap(bitmap);
        }
    }

    private void guardarEnBD() {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this);
        SQLiteDatabase db = admin.getWritableDatabase();

        ContentValues registro = new ContentValues();
        registro.put("titulo", etTitulo.getText().toString());
        registro.put("descripcion", etDescripcion.getText().toString());
        registro.put("latitud", latitud);
        registro.put("longitud", longitud);
        registro.put("foto_uri", currentPhotoPath);

        db.insert("reportes", null, registro);
        db.close();

        Toast.makeText(this, "Reporte Guardado", Toast.LENGTH_SHORT).show();
        finish(); // Cierra esta pantalla y vuelve al inicio
    }

    // Métodos vacíos requeridos por Interface LocationListener
    public void onStatusChanged(String provider, int status, Bundle extras) {}
    public void onProviderEnabled(@NonNull String provider) {}
    public void onProviderDisabled(@NonNull String provider) {}
}