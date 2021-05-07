package com.example.fridgetime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;

import com.example.fridgetime.activities.HomePage;
import com.example.fridgetime.activities.RegisterLogin;
import com.example.fridgetime.models.IsAuthGET;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainActivity extends AppCompatActivity {

    private static IsAuthGET isAuth;
    private static Future<JSONObject> sessionObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CookieHandler.setDefault(new CookieManager());
        isAuth = new IsAuthGET();
        sessionObject = isAuth.getSession(getApplicationContext());
        try {
            if (!sessionObject.get().getString("id").equals("noId")) {
                Intent intent = new Intent(MainActivity.this,
                        HomePage.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent(MainActivity.this,
                        RegisterLogin.class);
                startActivity(intent);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}