package com.example.ejercicio_clase_6.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.example.ejercicio_clase_6.R;
import com.example.ejercicio_clase_6.controller.EstudianteController;
import com.example.ejercicio_clase_6.controller.NotaController;
import com.example.ejercicio_clase_6.databinding.ActivityAgregarEstudiantesBinding;
import com.example.ejercicio_clase_6.databinding.ActivityDetallesEstudianteBinding;
import com.example.ejercicio_clase_6.model.Estudiante;
import com.example.ejercicio_clase_6.model.Nota;

import java.util.ArrayList;
import java.util.List;

public class DetallesEstudianteActivity extends AppCompatActivity {
    ActivityDetallesEstudianteBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetallesEstudianteBinding.inflate(getLayoutInflater());
        setContentView (binding.getRoot());

        NotaController notaController = new NotaController(this);
        EstudianteController estudianteController = new EstudianteController(this);


        ListView listNotas = binding.listaDeNotas;
        List<Nota> notas = new ArrayList<>();
        NotaListaAdapter notasAdapter = new NotaListaAdapter(this, notas );

        double promedio = notaController.calcularPromedio(notas);
        listNotas.setAdapter(notasAdapter);

        binding.verPromedioBtn.setOnClickListener(v -> {
            String codigoEstudiante = binding.codigoEstudiante.toString();
            Estudiante estudiante = estudianteController.obtenerEstudiantePorCodigo(codigoEstudiante);

            notas.clear();
            notas.addAll(notaController.obtenerNotasPorEstudiante(codigoEstudiante));
            binding.tvNombre.setText(estudiante.getNombre());
            binding.tvPromedio.setText(String.valueOf(promedio));
        });

        binding.btnVolver.setOnClickListener(v->{
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }
}