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
import com.example.ejercicio_clase_6.model.Nota;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    EstudianteController estudianteController = new EstudianteController(this);
    List<Estudiante> estudiantes = estudianteController.obtenerEstudiantes();

    NotaController notaController = new NotaController(this);
    ListView listView = binding.listaEstudiantes;
    EstudianteListaAdapter adapter = new EstudianteListaAdapter(estudiantes, this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView (binding.getRoot());

        binding.agregarBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AgregarEstudiantesActivity.class);
            startActivity(intent);
        });

        listView.setAdapter(adapter);

        binding.btnNotas.setOnClickListener(v->{
            Intent intent = new Intent (this, DetallesEstudianteActivity.class);
            startActivity(intent);
        });

        listView.setOnItemClickListener((parent, view, position, id)->{
            Estudiante estudianteSeleccionado = estudiantes.get(position);
            mostrarDialogoOpciones(estudianteSeleccionado);
        });

    }
    private void mostrarDialogoOpciones(Estudiante estudiante) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Selecciona una acción")
                .setItems(new CharSequence[]{"Editar datos", "Eliminar"}, (dialog, which) -> {
                    if (which == 0) {
                        // Editar
                        Intent intent = new Intent(this, EditarNotaActivity.class);
                        intent.putExtra("codEstudiante", estudiante.getCodigo()); // pasar el codigo
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
                    adapter.notifyDataSetChanged(); // actualiza la lista
                    Toast.makeText(this, "Estudiante eliminado!", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }
}