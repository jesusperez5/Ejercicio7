package com.example.ejercicio7;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TareaAdapter extends RecyclerView.Adapter<TareaAdapter.TareaViewHolder> {
    ArrayList<Tarea> tareas;
    OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(Tarea tarea);
    }

    public TareaAdapter(ArrayList<Tarea> tareas, OnItemClickListener listener){
        this.tareas = tareas;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TareaAdapter.TareaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TareaAdapter.TareaViewHolder tareaViewHolder = new TareaViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.tarea_view, parent, false));
        return tareaViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TareaAdapter.TareaViewHolder holder, int position) {
        Tarea tarea = tareas.get(position);
        holder.subject.setText(tarea.getAsignaturaTarea().toString());
        holder.text.setText(tarea.getTexto());
        holder.state.setText(tarea.getEstado().toString());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        holder.date.setText(tarea.getFecha().format(formatter));
    }

    @Override
    public int getItemCount() {
        return this.tareas.size();
    }

    public void addTask(Tarea tarea){
        System.out.println(tarea.getFecha());
        tareas.add(tarea);
        notifyItemInserted(tareas.size() - 1);
    }

    public void removeTask(Tarea tarea){
        int position = tareas.indexOf(tarea);
        tareas.remove(tarea);
        notifyItemRemoved(position);
    }

    public void updateToCompleated (Tarea tarea){
        int position = tareas.indexOf(tarea);
        tareas.get(position).setEstado(Tarea.State.COMPLETADO);
        notifyItemChanged(position);
    }

    public void updateTask (Tarea tarea, Tarea tareaNueva){
        int position = tareas.indexOf(tarea);
        tareas.get(position).setEstado(Tarea.State.PENDIENTE);
        tareas.get(position).setAsignaturaTarea(tareaNueva.getAsignaturaTarea());
        tareas.get(position).setFecha(tareaNueva.getFecha());
        tareas.get(position).setTexto(tareaNueva.getTexto());
        notifyItemChanged(position);
    }

    public class TareaViewHolder extends RecyclerView.ViewHolder{
        TextView subject;
        TextView text;
        TextView date;
        TextView state;

        public TareaViewHolder(@NonNull View itemView) {
            super(itemView);
            subject = itemView.findViewById(R.id.title);
            text = itemView.findViewById(R.id.text);
            date = itemView.findViewById(R.id.date);
            state = itemView.findViewById(R.id.state);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null)
                        listener.onItemClick(tareas.get(getAdapterPosition()));
                }
            });
        }
    }

}
