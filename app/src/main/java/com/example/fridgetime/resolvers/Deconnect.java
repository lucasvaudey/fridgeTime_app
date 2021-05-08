package com.example.fridgetime.resolvers;

import com.example.fridgetime.utils.JSONParser;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Deconnect {
    private static final JSONParser jsonParser = new JSONParser();
    private JSONObject response;
    private static final String REQUEST_URL = "http://192.168.9.169/~lucas/fridgeTime_serv/deconnect.php";
    private IsAuth isAuth;

    public Future<JSONObject> deconnexion(String sessionID) {
        isAuth = new IsAuth();
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            return executorService.submit(() -> {
                if(isAuth.isInternetAvailable()) {
                    ArrayList<BasicNameValuePair> params = new ArrayList<>();
                    params.add(new BasicNameValuePair("disconnect", sessionID));
                    response = jsonParser.makeHttpRequest(REQUEST_URL, "POST", params);
                    return response;
                }else {
                    response = new JSONObject();
                    response.put("success", 0);
                    return response;
                }
            });
    }
}
