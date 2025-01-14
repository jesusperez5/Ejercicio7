package com.example.ejercicio7;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyButtomSheetFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyButtomSheetFragment extends BottomSheetDialogFragment {

    public MyButtomSheetFragment() {
        // Required empty public constructor
    }

    public static MyButtomSheetFragment newInstance(String param1, String param2) {
        MyButtomSheetFragment fragment = new MyButtomSheetFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_buttom_sheet, container, false);
    }
}