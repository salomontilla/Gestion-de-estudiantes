package com.example.ejercicio_clase_6.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.ejercicio_clase_6.R;
import com.example.ejercicio_clase_6.controller.EstudianteController;
import com.example.ejercicio_clase_6.databinding.ActivityMainBinding;
import com.example.ejercicio_clase_6.model.Estudiante;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        EstudianteController estudianteController = new EstudianteController(this);


        ListView listView = findViewById(R.id.listaEstudiantes);

        List<Estudiante> estudiantes = estudianteController.obtenerEstudiantes();
        ArrayAdapter<Estudiante> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, estudiantes);

        listView.setAdapter(adapter);


        //setContentView(R.layout.activity_main_bd);
        setContentView (binding.getRoot());


    }
}