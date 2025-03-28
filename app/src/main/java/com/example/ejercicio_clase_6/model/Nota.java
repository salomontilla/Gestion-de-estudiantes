package com.example.ejercicio_clase_6.model;

public class Nota {
    private int id;
    private int estudianteId;
    private double valor;

    public Nota(int id, int estudianteId, double valor) {
        this.id = id;
        this.estudianteId = estudianteId;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(int estudianteId) {
        this.estudianteId = estudianteId;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
