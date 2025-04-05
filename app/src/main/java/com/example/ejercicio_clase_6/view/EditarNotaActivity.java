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

        binding.btnEditarNota.setOnClickListener(v->{
            String nuevaNota = String.valueOf(binding.inputNuevaNota.getText());
            if(nuevaNota.isEmpty()){
                Toast.makeText(getApplicationContext(), "Por favor, complete todos los campos.", Toast.LENGTH_SHORT).show();
            }else{
                System.out.println("ID de la nota que se va a editar: " + notaId);
                notaController.editarNota(notaId,Double.parseDouble(nuevaNota));
                Toast.makeText(getApplicationContext(), "Nota editada exitosamente.", Toast.LENGTH_SHORT).show();

            }
        });



        binding.btnVolverEditarNota.setOnClickListener(v-> {
            Intent intent = new Intent(this, DetallesEstudianteActivity.class);
            startActivity(intent);
        });
    }
}