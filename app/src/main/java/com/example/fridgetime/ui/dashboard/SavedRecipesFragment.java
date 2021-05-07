package com.example.fridgetime.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.fridgetime.R;

public class SavedRecipesFragment extends Fragment {

    private SavedRecipesViewModel savedRecipesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        savedRecipesViewModel =
                new ViewModelProvider(this).get(SavedRecipesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_saved_recipes, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        savedRecipesViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}