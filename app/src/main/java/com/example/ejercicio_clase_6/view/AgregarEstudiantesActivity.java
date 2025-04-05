package com.example.ejercicio_clase_6.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.ejercicio_clase_6.controller.EstudianteController;
import com.example.ejercicio_clase_6.controller.NotaController;
import com.example.ejercicio_clase_6.databinding.ActivityAgregarEstudiantesBinding;
import com.example.ejercicio_clase_6.model.Estudiante;

public class AgregarEstudiantesActivity extends AppCompatActivity {

    private ActivityAgregarEstudiantesBinding binding;
    private EstudianteController estudianteController = new EstudianteController(this);
    private NotaController notaController = new NotaController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAgregarEstudiantesBinding.inflate(getLayoutInflater());
        setContentView (binding.getRoot());

        //Accion agregar estudiante
        binding.agregarButton.setOnClickListener(view -> {
            String codigo = binding.codigoInput.getText().toString();
            String nombre = binding.nombreInput.getText().toString();
            String nota = binding.notaInput.getText().toString();

            if (codigo.isEmpty() || nombre.isEmpty() || nota.isEmpty()) {
                binding.codigoInput.setError("Este campo no puede estar vacio!");
                binding.nombreInput.setError("Este campo no puede estar vacio!");
                binding.notaInput.setError("Este campo no puede estar vacio!");
                //Toast.makeText(getApplicationContext(), "Por favor, complete todos los campos.", Toast.LENGTH_SHORT).show();
            }else {
                double notaValid = Double.parseDouble(nota);
                if((notaValid <1 || notaValid > 5)){//valida entre 1 y 5
                    binding.notaInput.setError("La nota debe ser entre 1 y 5!");
                }else{
                    Estudiante estudiante = estudianteController.obtenerEstudiantePorCodigo(codigo);
                    if(estudiante != null){//valida que no haya codigos en uso
                        binding.codigoInput.setError("Este codigo ya esta en uso!");
                    }else{
                        estudianteController.agregarEstudiante(nombre, codigo);
                        Estudiante nuevoEstudiante = estudianteController.obtenerEstudiantePorCodigo(codigo);
                        notaController.agregarNota(nuevoEstudiante.getId(), Double.parseDouble(nota));
                        Toast.makeText(getApplicationContext(), "Estudiante agregado exitosamente.",
                                Toast.LENGTH_SHORT).show();
                        binding.codigoInput.setText("");
                        binding.nombreInput.setText("");
                        binding.notaInput.setText("");
                    }
                }
            }
        });

        binding.volverBtn.setOnClickListener(view ->{
            Intent intent = new Intent(AgregarEstudiantesActivity.this, MainActivity.class);
            startActivity(intent);
        });


    }
}