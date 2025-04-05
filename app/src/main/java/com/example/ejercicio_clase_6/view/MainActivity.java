package com.example.ejercicio_clase_6.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ejercicio_clase_6.controller.EstudianteController;
import com.example.ejercicio_clase_6.controller.NotaController;
import com.example.ejercicio_clase_6.databinding.ActivityMainBinding;
import com.example.ejercicio_clase_6.model.Estudiante;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    EstudianteController estudianteController = new EstudianteController(this);
    NotaController notaController = new NotaController(this);

    List<Estudiante> estudiantes = new ArrayList<>();
    EstudianteListaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView (binding.getRoot());

        estudiantes = estudianteController.obtenerEstudiantes();

        binding.agregarBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AgregarEstudiantesActivity.class);
            startActivity(intent);
        });
        //Asigna el adapter a la list view
        ListView lvListaEstudiantes = binding.listaEstudiantes;
        adapter = new EstudianteListaAdapter(estudiantes, this);
        lvListaEstudiantes.setAdapter(adapter);

        binding.btnNotas.setOnClickListener(v->{
            Intent intent = new Intent (this, DetallesEstudianteActivity.class);
            startActivity(intent);
        });

        lvListaEstudiantes.setOnItemClickListener((parent, view, position, id)->{
            Estudiante estudianteSeleccionado = estudiantes.get(position);
            mostrarDialogoOpciones(estudianteSeleccionado);
        });

    }
    //Muestra dialogos de opciones
    private void mostrarDialogoOpciones(Estudiante estudiante) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Selecciona una acción")
                .setItems(new CharSequence[]{"Editar datos", "Eliminar"}, (dialog, which) -> {
                    if (which == 0) {
                        // Editar
                        Intent intent = new Intent(this, EditarEstudianteActivity.class);
                        intent.putExtra("codEstudiante", estudiante.getCodigo()); // pasar el codigo
                        System.out.println("Codigo estudiante: " + estudiante.getCodigo());
                        startActivity(intent);

                    } else if (which == 1) {
                        // Eliminar
                        mostrarDialogoConfirmacion(estudiante);
                    }
                })
                .show();
    }
    private void mostrarDialogoConfirmacion(Estudiante estudiante) {
        new AlertDialog.Builder(this)
                .setTitle("Confirmar eliminación")
                .setMessage("¿Estás seguro de eliminar esta nota?")
                .setPositiveButton("Sí", (dialog, which) -> {
                    estudianteController.eliminarEstudiante(estudiante.getId());
                    estudiantes.remove(estudiante);

                    estudiantes.clear();
                    estudiantes.addAll(estudianteController.obtenerEstudiantes());
                    adapter.notifyDataSetChanged(); // actualiza la lista

                    Toast.makeText(this, "Estudiante eliminado!", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }
}