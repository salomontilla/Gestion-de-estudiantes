package com.example.ejercicio_clase_6.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ejercicio_clase_6.model.DataBaseHelper;
import com.example.ejercicio_clase_6.model.Nota;

import java.util.ArrayList;
import java.util.List;

public class NotaController {

    private DataBaseHelper dbHelper;

    public NotaController(Context context) {
        dbHelper = new DataBaseHelper(context);
    }

    public void agregarNota(String estudianteId, double valor) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("estudiante_id", estudianteId);
        values.put("nota", valor);
        db.insert("notas", null, values);
        db.close();
    }

    public List<Nota> obtenerNotasPorEstudiante(String estudianteId) {
        List<Nota> lista = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM notas WHERE estudiante_id = ?", new String[]{String.valueOf(estudianteId)});

        if (cursor.moveToFirst()) {
            System.out.println("si hay nota");
            do {
                lista.add(new Nota(cursor.getInt(0), cursor.getInt(1), cursor.getDouble(2)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return lista;
    }

    public double calcularPromedio(List<Nota> notas) {
        if(notas.isEmpty()){
            System.out.println("notas vacias");
            return 0;
        }
        return (notas.stream()
                .mapToDouble(Nota::getValor)
                .sum())/notas.size();
    }

    public void eliminarNota(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        db.delete("notas", "id = ?",new String[]{String.valueOf(id)});
        db.close();
    }
}
