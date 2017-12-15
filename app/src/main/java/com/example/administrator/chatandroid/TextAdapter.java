package com.example.administrator.chatandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2016/8/23.
 */
public class TextAdapter extends BaseAdapter {
    private List<ListData> list;
    private Context context;
    private RelativeLayout relativeLayout;
    public TextAdapter(List<ListData> list,Context context){
        this.list = list;
        this.context = context;

    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        if(list.get(position).getFlag()==ListData.SEND) {
            relativeLayout = (RelativeLayout)inflater.inflate(R.layout.rightitem,null);
        }
        if(list.get(position).getFlag()==ListData.RECEIVE){
            relativeLayout = (RelativeLayout)inflater.inflate(R.layout.leftitem,null);
        }
        TextView tv = (TextView)relativeLayout.findViewById(R.id.text);
        TextView time = (TextView)relativeLayout.findViewById(R.id.time);
        tv.setText(list.get(position).getContent());
        time.setText(list.get(position).getTime());
        return relativeLayout;
    }
}
