package com.example.fridgetime.ui.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fridgetime.R;
import com.example.fridgetime.activities.BottomNavigation;
import com.example.fridgetime.activities.HomePage;
import com.example.fridgetime.resolvers.RegisterLoginPOST;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class RegisterLogin extends AppCompatActivity {

    private RegisterLoginPOST registerLoginPOST;
    private EditText email;
    private TextView email_title;
    private EditText username;
    private TextView username_title;
    private EditText password;
    private Button buttonChange;
    private Button lorR;
    private SharedPreferences sharedPreferences;
    private static Boolean login = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_login);
        email = findViewById(R.id.email_edit);
        email_title = findViewById(R.id.email_title);
        username_title = findViewById(R.id.username_title);
        username = findViewById(R.id.username_edit);
        password = findViewById(R.id.password_edit);
        buttonChange = findViewById(R.id.alreadyAnAccount);
        lorR = findViewById(R.id.registerLogin);
        registerLoginPOST = new RegisterLoginPOST();
        sharedPreferences = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
    }

    public void registerLogin(View view) throws ExecutionException, InterruptedException, JSONException {
        String usernameText = username.getText().toString();
        String emailText = email.getText().toString();
        String passwordText = password.getText().toString();
        if(emailText.equals("") && !login){
            email.setError("Le champs est vide.");
        }else
        if(usernameText.equals("")){
            username.setError("Le champs est vide.");
        }else
        if(passwordText.equals("")){
            password.setError("Le champs est vide.");
        }else {
            JSONObject jsonResponse;
            if(!login){
                jsonResponse = registerLoginPOST.register(usernameText, emailText, passwordText).get();
            }else{
                jsonResponse = registerLoginPOST.login(usernameText, passwordText).get();
            }
            switch (jsonResponse.getInt("success")) {
                case 0:
                    switch (jsonResponse.getString("field")) {
                        case "password":
                            password.setError(jsonResponse.getString("message"));
                            break;
                        case "email":
                            email.setError(jsonResponse.getString("message"));
                            break;
                        case "usernameOrEmail":
                            username.setError(jsonResponse.getString("message"));
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
                    Intent intent = new Intent(this, BottomNavigation.class);
                    sharedPreferences = getApplicationContext().getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("sessionID", jsonResponse.getString("sessionID"));
                    editor.apply();
                    startActivity(intent);
                    break;
            }
        }
    }

    public void loginLayout(View view) {
        login = !login;
        if(login){
            email.setVisibility(View.GONE);
            email.setText("");
            password.setText("");
            username.setText("");
            email_title.setVisibility(View.GONE);
            username_title.setText(R.string.usernameOrEmail);
            username.setHint(R.string.usernameOrEmail);
            buttonChange.setText(R.string.creer_un_compte);
            lorR.setText(R.string.se_connecter);
        }else{
            email.setVisibility(View.VISIBLE);
            email_title.setVisibility(View.VISIBLE);
            email.setText("");
            password.setText("");
            username.setText("");
            username_title.setText(R.string.username_title);
            username.setHint(R.string.username_title);
            buttonChange.setText(R.string.changeButton_text);
            lorR.setText(R.string.s_enregistrer);
        }
    }
}