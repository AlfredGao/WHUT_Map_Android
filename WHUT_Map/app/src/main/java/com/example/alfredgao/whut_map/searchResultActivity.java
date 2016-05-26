package com.example.alfredgao.whut_map;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.util.Log;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import java.util.Map;
import java.util.TreeMap;

public class searchResultActivity extends AppCompatActivity {
    List<campus_build_info> campusDataSet = campus_build_info.campus_build_infoList;
    List<campus_build_info> query_result_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        Bundle bundle = this.getIntent().getExtras();
        /*获取从搜索页面传递的搜索结果*/
        HashMap<String,Double> result_hs =  (HashMap)bundle.getSerializable("result_HashMap");


        /*用TreeMap进行排序并且新建comparator来修改进行降序排序*/
        SearchValueComparator svc = new SearchValueComparator(result_hs);
        TreeMap<String, Double> sort_result_hs = new TreeMap<>(svc);
        sort_result_hs.putAll(result_hs);

        /*将结果在搜索列表中显示*/
        ListView queryListView = (ListView)findViewById(R.id.query_result_listview);
        Log.i("query result test",String.valueOf(sort_result_hs));
        query_result_list = new ArrayList<>();
        for(String s : sort_result_hs.keySet()){
            Log.i("query result test",s);
            for (campus_build_info info:campusDataSet){
               // Log.i("query result test",info.getBuild_name());
                if(info.getBuild_name().equals(s)){
                    Log.i("query result test","hit!");
                    info.setQueryValue(result_hs.get(s));
                    query_result_list.add(info);
                }
            }
        }
        Log.i("query result test",String.valueOf(query_result_list));
        search_result_listadapter adapter = new search_result_listadapter(getApplicationContext(),query_result_list);
        queryListView.setAdapter(adapter);

        /*设置搜索列表点击事件 点击跳转至地图页面*/
        queryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int key = (int)view.getTag();
                List<campus_build_info> list = campus_build_info.campus_build_infoList;
                for(campus_build_info info:list){
                    if (info.getKey() == key){
                        Intent intent = new Intent(searchResultActivity.this,info_clickeditem.class);

                        Bundle bundle = new Bundle();
                        String name = info.getBuild_name();
                        Double lat = info.getMarkerLatitude();
                        Double lt = info.getMarkerLongtitude();
                        bundle.putString("BuildName", name);
                        bundle.putDouble("Latitude", lat);
                        bundle.putDouble("Longtitude",lt);

                        intent.putExtras(bundle);

                        startActivity(intent);
                    }
                }
            }
        });

    }

    /*新建comparator类来修改TreeMap的排序顺序*/
    class SearchValueComparator implements Comparator<String> {
        Map<String, Double> base;

        public SearchValueComparator(Map<String, Double> base) {
            this.base = base;
        }
        @Override
        public int compare(String lhs, String rhs) {
            //return 0;
            if (base.get(lhs) >= base.get(rhs)){
                return -1;
            }
            else {
                return 1;
            }
        }
    }
}
