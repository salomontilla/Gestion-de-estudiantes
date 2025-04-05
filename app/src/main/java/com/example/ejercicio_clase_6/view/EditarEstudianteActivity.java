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

        binding.btnEditarEst.setOnClickListener(v->{
            String nuevoNombre = binding.inputNuevoNombre.getText().toString();
            String nuevoCodigo = binding.inputNuevoCodigo.getText().toString();
            if(nuevoNombre.isEmpty() || nuevoCodigo.isEmpty()){
                Toast.makeText(this, "Completa todos los campos!", Toast.LENGTH_SHORT).show();
            }else{
                estudianteController.editarEstudiante(nuevoNombre, nuevoCodigo, codigoActual);
                Toast.makeText(this, "Estudiante Editado Exitosamente!", Toast.LENGTH_SHORT).show();

                tvNombreActual.setText(nuevoNombre);
                tvCodigoActual.setText(nuevoCodigo);
            }
        });

        binding.btnVolverEditarEst.setOnClickListener(v->{
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }
}