package com.example.ejercicio_clase_6.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ejercicio_clase_6.controller.NotaController;
import com.example.ejercicio_clase_6.databinding.ActivityEditarNotaBinding;

public class EditarNotaActivity extends AppCompatActivity {
    ActivityEditarNotaBinding binding;
    NotaController notaController = new NotaController(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditarNotaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int notaId = getIntent().getIntExtra("idNota", -1);
        double notaActual = getIntent().getDoubleExtra("notaActual", -1);


        binding.tvNombreActualNota.setText(String.valueOf(notaActual));

        binding.btnEditarNota.setOnClickListener(v -> {
            String nuevaNotaStr = binding.inputNuevaNota.getText().toString().trim();

            if (nuevaNotaStr.isEmpty()) {
                binding.inputNuevaNota.setError("Ingresa una nota!");
                return;
            }

            double nuevaNota = Double.parseDouble(nuevaNotaStr);;

            if (nuevaNota < 1.0 || nuevaNota > 5.0) {
                binding.inputNuevaNota.setError("La nota debe estar entre 1.0 y 5.0.");
                return;
            }

            notaController.editarNota(notaId, nuevaNota);
            Toast.makeText(getApplicationContext(), "Nota editada exitosamente.", Toast.LENGTH_SHORT).show();

            binding.inputNuevaNota.setText("");
        });



        binding.btnVolverEditarNota.setOnClickListener(v-> {
            Intent intent = new Intent(this, DetallesEstudianteActivity.class);
            startActivity(intent);
        });
    }
}