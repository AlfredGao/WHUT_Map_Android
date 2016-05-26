package com.example.alfredgao.whut_map;
import java.util.List;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by AlfredGao on 5/25/16.
 */
public class search_result_listadapter extends BaseAdapter {
    private Context context;
    private List<campus_build_info> mList;
    public search_result_listadapter(Context context, List<campus_build_info> mList){
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
        View v = View.inflate(context,R.layout.build_search_result_listcell,null);
        TextView buildName = (TextView)v.findViewById(R.id.search_result_cell_buildname);
        TextView campusName = (TextView)v.findViewById(R.id.search_result_cell_campusname);
        TextView searchType = (TextView)v.findViewById(R.id.search_result_type);
        buildName.setText(mList.get(position).getBuild_name());
        campusName.setText(mList.get(position).getCampus_id());
        double queryValue = mList.get(position).getQueryValue();
        if (accurQueryType(queryValue)) {
            searchType.setText("精确搜索结果");
            searchType.setTextColor(Color.rgb(225,51,41));
        } else {
            searchType.setText("你可能想找?");
            searchType.setTextColor(Color.rgb(38,130,213));

        }
        v.setTag(mList.get(position).getKey());
        return v;
    }

    public boolean accurQueryType(double queryValue){
        if (queryValue >= 10.0){
            return true;
        } else {
            return false;
        }
    }

}
