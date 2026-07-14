package com.example.georeporte;

public class Reporte {
    String titulo, descripcion, fotoUri, fecha;
    double latitud, longitud;

    public Reporte(String titulo, String descripcion, double latitud, double longitud, String fotoUri, String fecha) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.fotoUri = fotoUri;
        this.fecha = fecha;
    }
    // Getters simples
    public String getTitulo() { return titulo; }
    public String getFecha() { return fecha; }
    public String getFotoUri() { return fotoUri; }
    public double getLatitud() { return latitud; }
    public double getLongitud() { return longitud; }
}