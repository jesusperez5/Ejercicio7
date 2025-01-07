package com.example.ejercicio7;

import java.util.Date;

public class Tarea {
    private Subjects asignaturaTarea;
    private String texto;
    private Date fecha;
    private State estado;

    public enum Subjects{
        PMDM, AD, EIE, PDP, DI
    }

    public enum State {
        PENDIENTE, COMPLETADO
    }

    public Tarea(Subjects asignaturaTarea, Date fecha, String texto, State estado) {
        this.asignaturaTarea = asignaturaTarea;
        this.fecha = fecha;
        this.texto = texto;
        this.estado = estado;
    }

    public Subjects getAsignaturaTarea() {
        return asignaturaTarea;
    }

    public void setAsignaturaTarea(Subjects asignaturaTarea) {
        this.asignaturaTarea = asignaturaTarea;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public State getEstado() {
        return estado;
    }

    public void setEstado(State estado) {
        this.estado = estado;
    }
}
