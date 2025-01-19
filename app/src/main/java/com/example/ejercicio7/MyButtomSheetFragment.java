package com.example.ejercicio7;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyButtomSheetFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyButtomSheetFragment extends BottomSheetDialogFragment {

    private EditListener listener;
    private static final String ARG_TAREA = "tarea";
    private Tarea tarea;

    public MyButtomSheetFragment(Tarea tarea) {
        this.tarea = tarea;
    }

    public interface EditListener{
        void editTask(Tarea tarea, String action);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (EditListener) context;
    }

    public static MyButtomSheetFragment newInstance(Tarea tarea) {
        System.out.println("hola");
        MyButtomSheetFragment fragment = new MyButtomSheetFragment(tarea);
        Bundle args = new Bundle();
        args.putSerializable(ARG_TAREA, tarea);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null)
        tarea = (Tarea) getArguments().getSerializable(ARG_TAREA);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_buttom_sheet, container, false);

        LinearLayout edit = view.findViewById(R.id.editar);
        LinearLayout delete = view.findViewById(R.id.eliminar);
        LinearLayout compleat = view.findViewById(R.id.completado);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.editTask(tarea, "editar");
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.editTask(tarea, "borrar");
            }
        });
        compleat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.editTask(tarea, "completado");
            }
        });

        return view;
    }

}