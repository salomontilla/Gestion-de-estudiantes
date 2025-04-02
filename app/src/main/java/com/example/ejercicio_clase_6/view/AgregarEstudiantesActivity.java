package com.example.ejercicio_clase_6.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.ejercicio_clase_6.R;
import com.example.ejercicio_clase_6.controller.EstudianteController;
import com.example.ejercicio_clase_6.databinding.ActivityAgregarEstudiantesBinding;
import com.example.ejercicio_clase_6.databinding.ActivityMainBinding;

public class AgregarEstudiantesActivity extends AppCompatActivity {

    private ActivityAgregarEstudiantesBinding binding;
    private EstudianteController estudianteController = new EstudianteController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAgregarEstudiantesBinding.inflate(getLayoutInflater());
        setContentView (binding.getRoot());


        binding.agregarButton.setOnClickListener(view -> {
            String codigo = binding.codigoInput.getText().toString();
            String nombre = binding.nombreInput.getText().toString();
            String nota = binding.notaInput.getText().toString();
            if (codigo.isEmpty() || nombre.isEmpty() || nota.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Por favor, complete todos los campos.", Toast.LENGTH_SHORT).show();
            } else {
                estudianteController.agregarEstudiante(nombre, codigo);
                Toast.makeText(getApplicationContext(), "Estudiante agregado exitosamente.", Toast.LENGTH_SHORT).show();
            }
        });

        binding.volverBtn.setOnClickListener(view ->{
            Intent intent = new Intent(AgregarEstudiantesActivity.this, MainActivity.class);
            startActivity(intent);
        });


    }
}