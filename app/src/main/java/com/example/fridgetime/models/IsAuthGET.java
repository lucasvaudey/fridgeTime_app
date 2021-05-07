package com.example.fridgetime.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.fridgetime.MainActivity;
import com.example.fridgetime.R;
import com.example.fridgetime.utils.JSONParser;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class IsAuthGET {

    private static final JSONParser jsonParser = new JSONParser();
    private static final String REQUEST_URL = "http://192.168.1.25/~lucas/fridgeTime_serv/isAuth.php";
    private JSONObject response;
    private SharedPreferences sharedPreferences;

    public Future<JSONObject> getSession(Context context) {
        sharedPreferences = context.getSharedPreferences(context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        return executorService.submit(new Callable<JSONObject>() {
            @Override
            public JSONObject call() throws Exception {
                ArrayList<BasicNameValuePair> empty = new ArrayList<BasicNameValuePair>();
                boolean session = sharedPreferences.contains("sessionID");
                if (session) {
                    empty.add(new BasicNameValuePair("isAuth", sharedPreferences.getString("sessionID", null)));
                    response = jsonParser.makeHttpRequest(REQUEST_URL, "GET", empty);
                } else {
                    empty.add(new BasicNameValuePair("isAuth", null));
                    response = jsonParser.makeHttpRequest(REQUEST_URL, "GET", empty);
                }
                return response;
            }
        });
    }
}
