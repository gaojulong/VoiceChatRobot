package com.example.administrator.chatandroid;

import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2016/8/23.
 */
public class HttpData extends AsyncTask<String,Void,String> {

    private HttpURLConnection connection = null;
    private String url;
    private HttpDataListener listener;


    public HttpData(String url, HttpDataListener listener){
        this.url = url;
        this.listener = listener;
    }
    @Override
    protected String doInBackground(String... params) {

        try {
            URL url = new URL(this.url);
            connection =(HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(8000);
            connection.setReadTimeout(8000);
            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder builder = new StringBuilder();
            String line;
            while((line=reader.readLine())!=null){
                    builder.append(line);
            }
            System.out.println(builder.toString());
            return builder.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(String s) {
        listener.getData(s);
        super.onPostExecute(s);
    }
}
