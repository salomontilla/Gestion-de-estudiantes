package com.example.ejercicio_clase_6.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ejercicio_clase_6.controller.EstudianteController;
import com.example.ejercicio_clase_6.databinding.ActivityEditarEstudianteBinding;
import com.example.ejercicio_clase_6.model.Estudiante;

public class EditarEstudianteActivity extends AppCompatActivity {
    ActivityEditarEstudianteBinding binding;
    EstudianteController estudianteController = new EstudianteController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String codEstudiante = getIntent().getStringExtra("codEstudiante");
        super.onCreate(savedInstanceState);
        binding = ActivityEditarEstudianteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Estudiante estudiante = estudianteController.obtenerEstudiantePorCodigo(codEstudiante);

        String nombreActual = estudiante.getNombre();
        TextView tvNombreActual = binding.tvNombreActualEst;
        tvNombreActual.setText(nombreActual);

        String codigoActual = estudiante.getCodigo();
        TextView tvCodigoActual = binding.tvCodigoActual;
        tvCodigoActual.setText(codigoActual);

        binding.btnEditarEst.setOnClickListener(v -> {
            String nuevoNombre = binding.inputNuevoNombre.getText().toString().trim();
            String nuevoCodigo = binding.inputNuevoCodigo.getText().toString().trim();

            // Verifica si no hay cambios
            if (nuevoNombre.isEmpty() && nuevoCodigo.isEmpty()) {
                Toast.makeText(this, "No se detectaron cambios.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Usa los valores actuales si los inputs están vacíos
            if (nuevoNombre.isEmpty()) nuevoNombre = estudiante.getNombre();
            if (nuevoCodigo.isEmpty()) nuevoCodigo = estudiante.getCodigo();

            // Verifica si el código ingresado ya está en uso por otro estudiante
            Estudiante estudianteExistente = estudianteController.obtenerEstudiantePorCodigo(nuevoCodigo);
            if (estudianteExistente != null && estudianteExistente.getId() != estudiante.getId()) {
                binding.inputNuevoCodigo.setError("Este código ya está en uso.");
                return;
            }

            // Llama al método para editar
            estudianteController.editarEstudiante(nuevoNombre, nuevoCodigo, estudiante.getId());
            Toast.makeText(this, "Estudiante editado exitosamente.", Toast.LENGTH_SHORT).show();

            tvNombreActual.setText(nuevoNombre);
            tvCodigoActual.setText(nuevoCodigo);

            binding.inputNuevoNombre.setText("");
            binding.inputNuevoCodigo.setText("");
        });

        binding.btnVolverEditarEst.setOnClickListener(v->{
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }
}