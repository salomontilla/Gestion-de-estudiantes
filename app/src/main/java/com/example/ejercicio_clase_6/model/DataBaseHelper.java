package com.example.ejercicio_clase_6.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String NOMBRE_DB = "universidad.db";
    private static final int VERSION_DB = 1;


    public DataBaseHelper(Context context) {
        super(context, NOMBRE_DB, null, VERSION_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE estudiantes (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, codigo TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE notas (id INTEGER PRIMARY KEY AUTOINCREMENT, estudiante_id INTEGER, nota REAL," +
                " FOREIGN KEY (estudiante_id) REFERENCES estudiantes(id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS estudiantes");
        db.execSQL("DROP TABLE IF EXISTS notas");
        onCreate(db);
    }
}
