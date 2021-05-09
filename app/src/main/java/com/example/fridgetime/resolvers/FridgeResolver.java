package com.example.fridgetime.resolvers;

import com.example.fridgetime.utils.JSONParser;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FridgeResolver {

    private static final JSONParser jsonParser = new JSONParser();
    private JSONObject response;
    private static final String REQUEST_URL = "http://192.168.9.169/~lucas/fridgeTime_serv/fridge/fridge-request.php";

    public Future<JSONObject> getFridgeData(String sessionID) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        response = new JSONObject();
        return executorService.submit(new Callable<JSONObject>() {
            @Override
            public JSONObject call() throws Exception {
                ArrayList<BasicNameValuePair> params = new ArrayList<>();
                params.add(new BasicNameValuePair("sessionID", sessionID));
                params.add(new BasicNameValuePair("fridge", "notNull")); //TODO: Peut etre changer les conditions de requetes cote php
                response = jsonParser.makeHttpRequest(REQUEST_URL, "GET", params);
                return response;
            }
        });

    }
}
