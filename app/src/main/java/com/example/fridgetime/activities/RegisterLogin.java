package com.example.fridgetime.activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.fridgetime.models.IsAuthGET;
import com.example.fridgetime.models.RegisterLoginPOST;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fridgetime.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class RegisterLogin extends AppCompatActivity {

    private RegisterLoginPOST registerLoginPOST;
    private EditText email;
    private EditText username;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_login);
        email = findViewById(R.id.email_edit);
        username = findViewById(R.id.username_edit);
        password = findViewById(R.id.password_edit);
        registerLoginPOST = new RegisterLoginPOST();
    }

    public void register(View view) throws ExecutionException, InterruptedException, JSONException {
        String usernameText = username.getText().toString();
        String emailText = email.getText().toString();
        String passwordText = password.getText().toString();
        JSONObject jsonResponse;
        jsonResponse = registerLoginPOST.register(usernameText, emailText, passwordText).get();
        switch (jsonResponse.getInt("success")) {
            case 0:
                switch (jsonResponse.getString("field")) {
                    case "password":
                        password.setError(jsonResponse.getString("message"));
                        break;
                    case "email":
                        email.setError(jsonResponse.getString("message"));
                        break;
                    case "username":
                        username.setError(jsonResponse.getString("message"));
                        break;
                    case "none":
                        Toast.makeText(this, "Une erreur interne s'est produit veuillez réessayez" +
                                " plus tard", Toast.LENGTH_LONG).show();
                        break;
                }
                break;
            case 1:
                Intent intent = new Intent(this, HomePage.class);
                startActivity(intent);
                break;
        }

    }
}