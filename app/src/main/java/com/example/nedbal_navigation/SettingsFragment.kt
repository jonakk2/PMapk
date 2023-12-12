package com.example.nedbal_navigation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class SettingsFragment extends Fragment {
    private static final String FILE_NAME = "historie.txt";

    Button btnDelete;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        btnDelete = view.findViewById(R.id.btn_deleteHistory);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Deleted" + getActivity().getFilesDir() + "/" + FILE_NAME,
                        Toast.LENGTH_LONG).show();
                getActivity().deleteFile(FILE_NAME);
            }
        });

        return view;
    }
}