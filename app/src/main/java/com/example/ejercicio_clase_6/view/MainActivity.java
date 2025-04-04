package com.example.ejercicio_clase_6.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.example.ejercicio_clase_6.R;
import com.example.ejercicio_clase_6.controller.EstudianteController;
import com.example.ejercicio_clase_6.controller.NotaController;
import com.example.ejercicio_clase_6.databinding.ActivityMainBinding;
import com.example.ejercicio_clase_6.model.Estudiante;
import com.example.ejercicio_clase_6.model.Nota;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView (binding.getRoot());

        EstudianteController estudianteController = new EstudianteController(this);
        NotaController notaController = new NotaController(this);

        binding.agregarBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AgregarEstudiantesActivity.class);
            startActivity(intent);
        });


        ListView listView = binding.listaEstudiantes;
        List<Estudiante> estudiantes = estudianteController.obtenerEstudiantes();
        EstudianteListaAdapter adapter = new EstudianteListaAdapter(estudiantes, this);
        listView.setAdapter(adapter);

        binding.btnNotas.setOnClickListener(v->{
            Intent intent = new Intent (this, DetallesEstudianteActivity.class);
            startActivity(intent);
        });



    }
}