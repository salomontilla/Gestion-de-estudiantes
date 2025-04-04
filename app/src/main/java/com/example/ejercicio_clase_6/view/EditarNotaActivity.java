package com.example.ejercicio_clase_6.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ejercicio_clase_6.databinding.ActivityEditarNotaBinding;

public class EditarNotaActivity extends AppCompatActivity {
    ActivityEditarNotaBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditarNotaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



    }
}