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
        values.put("valor", valor);
        db.insert("notas", null, values);
        db.close();
    }

    public List<Nota> obtenerNotasPorEstudiante(int estudianteId) {
        List<Nota> lista = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM notas WHERE estudiante_id = ?", new String[]{String.valueOf(estudianteId)});

        if (cursor.moveToFirst()) {
            do {
                lista.add(new Nota(cursor.getInt(0), cursor.getInt(1), cursor.getDouble(2)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return lista;
    }

}
