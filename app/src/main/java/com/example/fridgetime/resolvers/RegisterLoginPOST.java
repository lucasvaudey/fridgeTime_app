package com.example.fridgetime.resolvers;

import com.example.fridgetime.utils.JSONParser;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class RegisterLoginPOST {
    private static final String REQUEST_URL = "http://192.168.1.25/~lucas/fridgeTime_serv/login-register.php";
    private final JSONParser jsonParser = new JSONParser();
    private JSONObject json = null;

    public RegisterLoginPOST(){
        super();
    }

    public Future<JSONObject> login(String usernameOrEmail, String password){
        ArrayList<BasicNameValuePair> params = new ArrayList<>();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        return executorService.submit(() -> {
            params.add(new BasicNameValuePair("username", usernameOrEmail));
            params.add(new BasicNameValuePair("password", password));
            json = jsonParser.makeHttpRequest(REQUEST_URL, "POST", params);
            return json;
        });
    }

    public Future<JSONObject> register(String username, String email, String password){
        ArrayList<BasicNameValuePair> params = new ArrayList<>();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        return executorService.submit(() -> {
            params.add(new BasicNameValuePair("username", username));
            params.add(new BasicNameValuePair("password", password));
            params.add(new BasicNameValuePair("email", email));
            json = jsonParser.makeHttpRequest(REQUEST_URL, "POST", params);
            return  json;
        });
    }
}
