package com.example.fridgetime.models;

import com.example.fridgetime.utils.JSONParser;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class IsAuthGET {

    private static JSONParser jsonParser = new JSONParser();
    private static final String REQUEST_URL = "http://192.168.1.67/fridgetime/isAuth.php";
    private JSONObject response;

    public Future<JSONObject> getSession() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
          Future<JSONObject> jsonObjectFuture = executorService.submit(new Callable<JSONObject>() {
             @Override
             public JSONObject call() throws Exception {
                 ArrayList<BasicNameValuePair> empty = new ArrayList<BasicNameValuePair>();
                 empty.add(new BasicNameValuePair("isAuth", ""));
                 response = jsonParser.makeHttpRequest(REQUEST_URL, "GET", empty);
                 return response;
             }
        });
          return jsonObjectFuture;
    }
}
