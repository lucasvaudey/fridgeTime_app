package com.example.fridgetime.resolvers;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;

import com.example.fridgetime.R;
import com.example.fridgetime.utils.JSONParser;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class IsAuth {

    private static final JSONParser jsonParser = new JSONParser();
    private static final String REQUEST_URL = "http://192.168.9.169/~lucas/fridgeTime_serv/isAuth.php";
    private JSONObject response;
    private SharedPreferences sharedPreferences;

    public Future<JSONObject> getSession(Context context) {
        sharedPreferences = context.getSharedPreferences(context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            return executorService.submit(() -> {
                ArrayList<BasicNameValuePair> sessionID = new ArrayList<>();
                boolean session = sharedPreferences.contains("sessionID");
                if (session) {
                    if (isInternetAvailable()) {
                        sessionID.add(new BasicNameValuePair("isAuth", sharedPreferences.getString("sessionID", null)));
                        response = jsonParser.makeHttpRequest(REQUEST_URL, "POST", sessionID);
                    }else{
                         response = new JSONObject();
                        response.put("id", sharedPreferences.getString("sessionID", null));
                        response.put("username", sharedPreferences.getString("username", null));
                    }
                } else {
                    if(isInternetAvailable()){
                        sessionID.add(new BasicNameValuePair("isAuth", null));
                        response = jsonParser.makeHttpRequest(REQUEST_URL, "POST", sessionID);
                    }else{
                        response = new JSONObject();
                        response.put("id", "noId");
                    }
                }
                return response;
            });
    }

    public boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com");
            return !ipAddr.equals("");

        } catch (Exception e) {
            return false;
        }
    }
}
