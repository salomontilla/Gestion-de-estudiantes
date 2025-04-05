package com.example.ejercicio_clase_6.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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
        Estudiante estudiante = null; // Solo lo creamos si lo encontramos
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM estudiantes WHERE codigo = ?", new String[]{codigo});

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                estudiante = new Estudiante(
                        cursor.getInt(0),    // id
                        cursor.getString(1), // nombre
                        cursor.getString(2)  // codigo
                );
                Log.d("DB", "Estudiante encontrado: " + estudiante.getNombre());
            } else {
                Log.d("DB", "Estudiante no encontrado con código: " + codigo);
            }
            cursor.close();
        }

        db.close();
        return estudiante;
    }

    public void eliminarEstudiante(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        db.delete("estudiantes", "id = ?",new String[]{String.valueOf(id)});
        db.close();
    }

    public void editarEstudiante(String nuevoNombre, String nuevoCodigo, String codigoActual){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("nombre", nuevoNombre);
        valores.put("codigo", nuevoCodigo);
        db.update("notas", valores, "id = ?", new String[]{codigoActual});
        db.close();
    }
}
