package com.example.ejercicio_clase_6.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ejercicio_clase_6.model.DataBaseHelper;
import com.example.ejercicio_clase_6.model.Estudiante;

import java.util.ArrayList;
import java.util.List;

public class EstudianteController {
    private DataBaseHelper dbHelper;

    public EstudianteController(Context context) {
        dbHelper = new DataBaseHelper(context);
    }

    public void agregarEstudiante(String nombre, String codigo) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("codigo", codigo);
        db.insert("estudiantes", null, values);
        db.close();
    }

    public List<Estudiante> obtenerEstudiantes() {
        List<Estudiante> lista = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM estudiantes", null);

        if (cursor.moveToFirst()) {
            do {
                lista.add(new Estudiante(cursor.getInt(0), cursor.getString(1), cursor.getString(2)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return lista;
    }

    public Estudiante obtenerEstudiantePorCodigo(String codigo){
        Estudiante estudiante = new Estudiante();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM estudiantes WHERE codigo = ?", new String[]{String.valueOf(codigo)});
        if (cursor.moveToNext()){
            estudiante = new Estudiante(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
        }
        return estudiante;
    }
}
