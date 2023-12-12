package com.example.nedbal_navigation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class AboutFragment extends Fragment {
    TextView api;
    TextView icon;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        api = view.findViewById(R.id.api);
        api.setMovementMethod(LinkMovementMethod.getInstance());

        icon = view.findViewById(R.id.icon);
        icon.setMovementMethod(LinkMovementMethod.getInstance());

        return view;
    }
}