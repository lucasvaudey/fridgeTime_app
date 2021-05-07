package com.example.fridgetime.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.fridgetime.R;
import com.example.fridgetime.resolvers.Deconnect;
import com.example.fridgetime.ui.login.RegisterLogin;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class BottomNavigation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);
    }
    public void disconnect(View view) throws ExecutionException, InterruptedException, JSONException {
        SharedPreferences sharedPreferences = this.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        Deconnect deconnect = new Deconnect();
        JSONObject jsonObject;
        jsonObject = deconnect.deconnexion(sharedPreferences.getString(getString(R.string.preference_sessionID_key), null)).get();
        switch (jsonObject.getInt("success")){
            case 0:
                Log.d("errorrr", "ca ne marche pas");
                break;
            case 1:
                //Destruction de  les valeurs stockées dans les sharedPrefs.
                sharedPreferences.edit().clear().apply();
                //Retour sur la page Register et login.
                Intent intent = new Intent(this, RegisterLogin.class);
                startActivity(intent);
                break;
        }


    }


}