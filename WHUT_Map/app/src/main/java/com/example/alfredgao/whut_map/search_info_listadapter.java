package com.example.alfredgao.whut_map;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import java.util.ArrayList;

/**
 * Created by AlfredGao on 5/24/16.
 */
public class search_info_listadapter extends BaseAdapter {

    private Context context;
    private List<String> mList;

    public search_info_listadapter(Context context, List<String> mList){
        this.context = context;
        this.mList = mList;
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(context,R.layout.build_search_listcell,null);
        TextView build_search_name = (TextView)v.findViewById(R.id.build_info_searchname);
        build_search_name.setText(mList.get(position));
        v.setTag(mList.get(position));
        return v;
    }
}
