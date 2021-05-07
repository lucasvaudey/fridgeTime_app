package com.example.fridgetime.utils;

import android.content.SharedPreferences;
import android.util.Log;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpCookie;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.zip.Deflater;

public class JSONParser {

    static InputStream inputStream = null;
    static JSONObject jObj = null;
    static String json = "";

    public JSONParser() {
        super();
    }

    /**
     * Créer une requete http sur le serveur en ciblant l'URL du backend et la method
     * @param url
     * @param method
     * @param params
     * @return
     */
    public JSONObject makeHttpRequest(String url, String method, ArrayList params) {
        try {
            if (method.equals("POST")) {
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(url);
                httpPost.setEntity(new UrlEncodedFormEntity(params));
                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                inputStream = httpEntity.getContent();
            } else if (method.equals("GET")) {
                DefaultHttpClient httpClient = new DefaultHttpClient();
                String parmaString = URLEncodedUtils.format(params, "utf-8");
                url += "?" + parmaString;
                HttpGet httpGet = new HttpGet(url);
                HttpResponse httpResponse = httpClient.execute(httpGet);
                HttpEntity httpEntity = httpResponse.getEntity();
                inputStream = httpEntity.getContent();
            }
        } catch (UnsupportedEncodingException e) {
            Log.d("encodage", e.toString());
        } catch (ClientProtocolException e) {
            Log.d("protocole", e.toString());
        } catch (IOException e) {
            Log.d("I/O", e.toString());
        }

       try {
           json = convertStreamToString(inputStream);
           Log.d("jsonRESPONSE", json);
       } catch (Exception e) {
           Log.d("convertStream", e.toString());
       }

        try {
            jObj = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jObj;
    }

    /**
     * Converti les inputs stream en string et ferme l'inputStream
     * @param is
     * @return
     * @throws Exception
     */
    private String convertStreamToString(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        is.close();
        return sb.toString();
    }
}
