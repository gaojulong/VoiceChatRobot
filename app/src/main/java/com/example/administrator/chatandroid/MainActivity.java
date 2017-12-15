package com.example.administrator.chatandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements HttpDataListener,View.OnClickListener{
    private HttpData httpData;
    private List<ListData> list;

    private ListView listView;
    private EditText editText;
    private Button button;

    private String content;
    private TextAdapter adapter;

    private String []welcome_array;
    private String url = "http://www.tuling123.com/openapi/api?key=1c21b0606d78455c8760136c8dadfd70&info=";

    private double currentTime,oldTime=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();


    }
    private  void init(){
        list = new ArrayList<ListData>();
        listView = (ListView)findViewById(R.id.listview);
        button = (Button)findViewById(R.id.btn);
        button.setOnClickListener(this);
        editText=(EditText)findViewById(R.id.editText);
        adapter = new TextAdapter(list,this);
        listView.setAdapter(adapter);
        ListData listData = new ListData(RandWelcomeTips(),ListData.RECEIVE,getTime());
        list.add(listData);


    }
    private String RandWelcomeTips(){
        String welcome_tip = null;
         welcome_array = this.getResources().getStringArray(R.array.welcome);
        int index = (int)(Math.random()*(welcome_array.length-1));
        welcome_tip=welcome_array[index];
        return welcome_tip;
    }
    @Override
    public void getData(String data) {
        System.out.println(data);
        paseText(data);
    }
    public void paseText(String data){
        try {
            JSONObject jsonObject = new JSONObject(data);
   /*         System.out.println(jsonObject.getString("code"));
            System.out.println(jsonObject.getString("text"));*/
            ListData listData = new ListData(jsonObject.getString("text"),ListData.RECEIVE,getTime());
            list.add(listData);
            adapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {
        String s1 =null;
        content= editText.getText().toString();
        String text = content.replace(" ","");
        String text1 = text.replace("\n","");
        try {
           s1 = URLEncoder.encode(text1, "utf-8");
            ListData listDdata = new ListData(content,ListData.SEND,getTime());
            editText.setText("");
            list.add(listDdata);
            adapter.notifyDataSetChanged();
            httpData =(HttpData) new HttpData("http://www.tuling123.com/openapi/api?key=1c21b0606d78455c8760136c8dadfd70&info="+s1,this).execute();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
    public String  getTime(){
        currentTime = System.currentTimeMillis();
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date date = new Date();
        String str = format.format(date);
        if(currentTime-oldTime>=1000*15){
            oldTime = currentTime;
            return str;
        }
        return "";
    }
}
