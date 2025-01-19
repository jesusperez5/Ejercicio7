package com.example.ejercicio7;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Edit extends DialogFragment {

    public interface EditDialogListener {
        void onEditDialogPositiveClick(Tarea tarea, Tarea tareaNueva);
    }

    private Edit.EditDialogListener listener;

    private EditText inputDate;
    private EditText inputTask;
    private Spinner inputSubject;

    private Tarea tarea;

    public void setTarea(Tarea tarea) {
        this.tarea = tarea;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater layoutInflater = requireActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.add_fragment, null);
        builder.setView(view);

        inputDate = view.findViewById(R.id.dateInput);
        inputSubject = view.findViewById(R.id.subjectToggle);
        inputTask = view.findViewById(R.id.inputTask);

        List<String> opciones = Arrays.asList(
                Tarea.Subjects.AD.toString(), Tarea.Subjects.DI.toString(), Tarea.Subjects.EIE.toString(),
                Tarea.Subjects.PDP.toString(), Tarea.Subjects.PMDM.toString()
        );
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                opciones
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        inputSubject.setAdapter(adapter);
        inputDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDatePickerDialog();
            }
        });

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        inputDate.setText(tarea.getFecha().format(formatter));
        inputTask.setText(tarea.getTexto());
        inputSubject.setSelection(opciones.indexOf(tarea.getAsignaturaTarea().toString()));



        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        if(inputDate.getText() != null && inputTask.getText() != null){
                            try {
                                String date = String.valueOf(inputDate.getText());
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                                LocalDate parsedDate = LocalDate.parse(date, formatter);
                                String task = String.valueOf(inputTask.getText());
                                String subjectString = String.valueOf(inputSubject.getSelectedItem().toString());
                                Tarea.Subjects subject = Tarea.Subjects.AD;
                                for (Tarea.Subjects asignatura:
                                        Tarea.Subjects.values()) {
                                    if(subjectString == asignatura.toString()){
                                        subject = asignatura;
                                    }
                                }
                                Tarea tareaNueva = new Tarea(
                                        subject,
                                        parsedDate,
                                        task,
                                        Tarea.State.PENDIENTE
                                );
                                listener.onEditDialogPositiveClick(tarea, tareaNueva);
                            } catch (Exception e){
                                e.printStackTrace();
                            }
                        }

                    }
                })
                .setNegativeButton("Rechazar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Edit.this.getDialog().cancel();
                    }
                });




        return builder.create();

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (Edit.EditDialogListener) context;
    }

    private void mostrarDatePickerDialog() {
        final Calendar calendario = Calendar.getInstance();
        int año = calendario.get(Calendar.YEAR);
        int mes = calendario.get(Calendar.MONTH);
        int dia = calendario.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                requireContext(),
                (view, añoSeleccionado, mesSeleccionado, diaSeleccionado) -> {
                    // Formatear fecha seleccionada
                    String fechaSeleccionada = String.format("%02d/%02d/%04d", diaSeleccionado, mesSeleccionado + 1, añoSeleccionado);
                    System.out.println("dataPicker " + fechaSeleccionada);
                    inputDate.setText(fechaSeleccionada);
                },
                año, mes, dia);

        datePickerDialog.show();
    }
}