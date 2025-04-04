package com.example.ejercicio_clase_6.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

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
        setContentView(binding.getRoot());

        NotaController notaController = new NotaController(this);
        EstudianteController estudianteController = new EstudianteController(this);

        ListView listNotas = binding.listaDeNotas;
        List<Nota> notas = new ArrayList<>();
        NotaListaAdapter notasAdapter = new NotaListaAdapter(this, notas);
        listNotas.setAdapter(notasAdapter);

        binding.verPromedioBtn.setOnClickListener(v -> {
            String codigoEstudiante = binding.codigoEstudiante.getText().toString().trim();

            if (codigoEstudiante.isEmpty()) {
                Toast.makeText(this, "Por favor ingresa un código", Toast.LENGTH_SHORT).show();
                return;
            }

            Estudiante estudiante = estudianteController.obtenerEstudiantePorCodigo(codigoEstudiante);

            if (estudiante == null) {
                Toast.makeText(this, "Estudiante no encontrado", Toast.LENGTH_SHORT).show();
                return;
            }

            // Actualiza las notas del estudiante
            notas.clear();
            notas.addAll(notaController.obtenerNotasPorEstudiante(codigoEstudiante));
            notasAdapter.notifyDataSetChanged(); // Muy importante

            // Calcula el promedio con las notas actualizadas
            double promedio = notaController.calcularPromedio(notas);

            // Muestra los datos
            binding.tvNombre.setText(estudiante.getNombre());
            binding.tvPromedio.setText(String.format("%.2f", promedio));
        });

        binding.btnVolver.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }
}