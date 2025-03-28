package com.example.ejercicio_clase_6.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.ejercicio_clase_6.R;
import com.example.ejercicio_clase_6.controller.EstudianteController;
import com.example.ejercicio_clase_6.databinding.ActivityAgregarEstudiantesBinding;
import com.example.ejercicio_clase_6.databinding.ActivityMainBinding;

public class AgregarEstudiantesActivity extends AppCompatActivity {

    private ActivityAgregarEstudiantesBinding binding;
    private EstudianteController estudianteController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_estudiantes);
        binding = ActivityAgregarEstudiantesBinding.inflate(getLayoutInflater());
        setContentView (binding.getRoot());

        String codigo = binding.codigoInput.getText().toString();
        String nombre = binding.nombreInput.getText().toString();
        int nota = Integer.parseInt(binding.notaInput.getText().toString()) ;

        binding.agregarButton.setOnClickListener(view -> {
            if (codigo.isEmpty() || nombre.isEmpty() || binding.notaInput.getText().toString().isEmpty()) {
                Toast.makeText(getApplicationContext(), "Por favor, complete todos los campos.", Toast.LENGTH_SHORT).show();
            } else {
                estudianteController.agregarEstudiante(nombre, codigo);
                Toast.makeText(getApplicationContext(), "Estudiante agregado exitosamente.", Toast.LENGTH_SHORT).show();
            }
        });


    }
}