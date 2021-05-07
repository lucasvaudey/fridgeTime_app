package com.example.fridgetime.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.fridgetime.R;
import com.example.fridgetime.models.IsAuthGET;

import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class HomePage extends AppCompatActivity {
    private IsAuthGET isAuthGET;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        isAuthGET = new IsAuthGET();
        try {
            JSONObject json = isAuthGET.getSession(getApplicationContext()).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}