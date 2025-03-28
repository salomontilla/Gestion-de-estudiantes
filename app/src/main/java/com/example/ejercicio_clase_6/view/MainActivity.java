package com.example.ejercicio_clase_6.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.ejercicio_clase_6.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        //setContentView(R.layout.activity_main_bd);
        setContentView (binding.getRoot());
        binding.btnSaludar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                binding.tvMessage.setText("Hola");
            }
        });

    }
}