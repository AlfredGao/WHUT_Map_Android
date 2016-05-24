package com.example.alfredgao.whut_map;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by AlfredGao on 5/23/16.
 */
public class build_info_listadapter extends BaseAdapter {
    private Context mcontext;
    private List<campus_build_info> mInfoList;

    public build_info_listadapter(Context context, List<campus_build_info> mInfoList){
        this.mcontext = context;
        this.mInfoList = mInfoList;
    }

    @Override
    public int getCount() {
        return mInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return mInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //return null;
        View v = View.inflate(mcontext, R.layout.build_info_listcell,null);
        TextView buildName = (TextView)v.findViewById(R.id.list_cell_buildname);
        TextView campusName = (TextView)v.findViewById(R.id.list_cell_campusname);

        buildName.setText(mInfoList.get(position).getBuild_name());
        campusName.setText(mInfoList.get(position).getCampus_id());

        v.setTag(mInfoList.get(position).getKey());

        return v;
    }
}
