package com.example.ejercicio7;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements TareaAdapter.OnItemClickListener, Add.AddDialogListener, MyButtomSheetFragment.EditListener, Edit.EditDialogListener{

    private ArrayList<Tarea> tareas;
    private TareaAdapter tareaAdapter;
    private MyButtomSheetFragment buttomSheet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tareas = new ArrayList<>();
        tareaAdapter = new TareaAdapter(tareas, this);
        RecyclerView recyclerView = findViewById(R.id.recyclerTareas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(tareaAdapter);

        // Configuración FAB
        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new Add();
                dialogFragment.show(getSupportFragmentManager(), "");
            }
        });

    }


    @Override
    public void onItemClick(Tarea tarea) {
        Toast.makeText(this, tarea.getTexto(), Toast.LENGTH_SHORT).show();
        buttomSheet = new MyButtomSheetFragment(tarea);
        buttomSheet.show(getSupportFragmentManager(), buttomSheet.getTag());

    }

    @Override
    public void onDialogPositiveClick(Tarea tarea) {
        Toast.makeText(this, tarea.getTexto(), Toast.LENGTH_SHORT).show();
        tareaAdapter.addTask(tarea);
    }

    @Override
    public void editTask(Tarea tarea, String action) {
        if(action == "borrar"){
            System.out.println("borrar");
            tareas.remove(tarea);
            this.tareaAdapter.removeTask(tarea);
            buttomSheet.dismiss();
        } else if(action == "editar"){
            Edit dialogFragment = new Edit();
            dialogFragment.show(getSupportFragmentManager(), "");
            dialogFragment.setTarea(tarea);
            buttomSheet.dismiss();
        } else if(action == "completado"){
           this.tareaAdapter.updateToCompleated(tarea);
            buttomSheet.dismiss();
        }

    }

    @Override
    public void onEditDialogPositiveClick(Tarea tarea, Tarea tareaNueva) {
        this.tareaAdapter.updateTask(tarea, tareaNueva);
    }
}