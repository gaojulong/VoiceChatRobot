package com.example.administrator.chatandroid;

/**
 * Created by Administrator on 2016/8/23.
 */
public class ListData {
    public static final  int SEND=1;
    public static final int RECEIVE=2;
    private String content;
    private int flag;
    private  String time;
    public ListData(String content,int flag,String time){
        setContent(content);
        setFlag(flag);
        setTime(time);
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getContent() {
        return content;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getFlag() {
        return flag;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }
}
