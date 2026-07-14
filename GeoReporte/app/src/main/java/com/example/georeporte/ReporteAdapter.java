package com.example.georeporte;

import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ReporteAdapter extends RecyclerView.Adapter<ReporteAdapter.ViewHolder> {

    List<Reporte> listaReportes;

    public ReporteAdapter(List<Reporte> listaReportes) {
        this.listaReportes = listaReportes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reporte, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Reporte reporte = listaReportes.get(position);
        holder.tvTitulo.setText(reporte.getTitulo());
        holder.tvFecha.setText(reporte.getFecha());
        // <--- NUEVA LÍNEA: Mostrar latitud y longitud
        holder.tvUbicacion.setText("Ubicación: " + reporte.getLatitud() + ", " + reporte.getLongitud());


        if (reporte.getFotoUri() != null) {
            holder.imgFoto.setImageBitmap(BitmapFactory.decodeFile(reporte.getFotoUri()));
        }
    }

    @Override
    public int getItemCount() { return listaReportes.size(); }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitulo, tvFecha, tvUbicacion;
        ImageView imgFoto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitulo = itemView.findViewById(R.id.txtTituloItem);
            tvFecha = itemView.findViewById(R.id.txtFechaItem);
            tvUbicacion = itemView.findViewById(R.id.txtUbicacionItem); // <--- Lo conectamos con el XML
            imgFoto = itemView.findViewById(R.id.imgMiniatura);
        }
    }
}