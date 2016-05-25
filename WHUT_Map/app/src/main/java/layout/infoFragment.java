package layout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.HashMap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.util.Log;
import android.widget.SearchView;

import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.example.alfredgao.whut_map.R;
import com.example.alfredgao.whut_map.build_info_listadapter;
import com.example.alfredgao.whut_map.campus_build_info;
import com.example.alfredgao.whut_map.info_clickeditem;
import com.example.alfredgao.whut_map.search_info_listadapter;



/**
 * A simple {@link Fragment} subclass.
 */
public class infoFragment extends Fragment implements SearchView.OnQueryTextListener {
    private String[] mStrings = {"鉴湖主教学楼",
            "鉴湖一教学楼",
            "鉴湖三教学楼",
            "鉴湖学府超市",
            "学海公寓",
            "武汉理工大学西区足球场",
            "西院教1楼",
            "资源与环境工程学院",
            "武汉理工大学活动中心",
            "武汉理工大学网球场",
            "西院图书馆",
            "新一教学楼",
            "新二教学楼",
            "新四教学楼",
            "学子苑餐厅（南湖食堂）",
            "博学广场",
            "理学院",
            "力学楼"};
    private ListView info_searchAdapter;
    private SearchView info_search;
    public infoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        ListView info_list_view = (ListView)view.findViewById(R.id.listView);
        List<campus_build_info> mList = campus_build_info.campus_build_infoList;

        build_info_listadapter adapter = new build_info_listadapter(getActivity().getApplicationContext(),mList);
        info_list_view.setAdapter(adapter);

        info_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("Info ListView Test",String.valueOf(view.getTag()));
                int key = (int)view.getTag();
                List<campus_build_info> list = campus_build_info.campus_build_infoList;
                for(campus_build_info info:list){
                    if (info.getKey() == key){
                        Intent intent = new Intent(getActivity(),info_clickeditem.class);

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


        info_search = (SearchView)view.findViewById(R.id.info_searchView);
        info_search.setOnQueryTextListener(this);
        info_searchAdapter = (ListView)view.findViewById(R.id.info_searchView_Adapter);
        info_searchAdapter.setVisibility(View.GONE);


        return view;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        ArrayList<Integer> queryResult = new ArrayList<>();
        HashMap<String, Integer> showResult = new HashMap<>();
        for (String target:mStrings){
            int queryValue = minDistance(query , target);
            showResult.put(target,queryValue);
            queryResult.add(queryValue);
            Collections.sort(queryResult);
        }
        Log.i("Query Test" , String.valueOf(queryResult));

        for (Integer value: queryResult){

        }
        return true;
    }
    //Edit Distance 搜索算法
    public int minDistance(String word1, String word2) {
        //int Max = Integer.MIN_VALUE;
        //int Max_length;

        if (word1.length() == 0 || word2.length() == 0) {
            return word1.length() + word2.length();
        }

        int [][] distance = new int [word1.length() + 1][word2.length() + 1];

        for (int i = 0; i <= word1.length(); i++){
            distance[i][0] = i;
        }

        for (int i = 0;i <= word2.length(); i++) {
            distance[0][i] = i;
        }

        for(int i = 0;i < word1.length(); i++){
            //int min = Integer.MAX_VALUE;
            for (int j = 0;j < word2.length();j++){
                if (word1.charAt(i) == word2.charAt(j)){
                    distance[i+1][j+1] = distance[i][j];
                }
                else {
                    distance[i+1][j+1] = Math.min(Math.min(distance[i+1][j]+1 , distance[i][j+1]+1), distance[i][j]+1);
                }
            }
        }

        return distance[word1.length()][word2.length()];
    }


    @Override
    public boolean onQueryTextChange(String newText) {
        Log.i("Search Test",newText);

        if (newText.length() > 0){
            //初始化自动补全字典树数据结构
            Trie trie = new Trie();
            List<String> mList = new ArrayList<>();
            List<String> resultList = new ArrayList<>();

            for(String s:mStrings){
                mList.add(s);
                trie.insert(s);
            }

            TrieNode tNode = trie.searchNode(newText);
            if (tNode != null) {
                //HashMap<Character, TrieNode> hs = tNode.child;

                resultList = trie.showSamePrefix(tNode);
                if (newText.length() > 1){
                    List<String> temp_list = new ArrayList<>();
                    for (String s: resultList){
                        String prefix = newText.substring(0,newText.length() - 1);
                        prefix += s;
                        temp_list.add(prefix);

                    }
                    resultList = temp_list;
                }
                Log.i("Search Test",String.valueOf(resultList));
            }

            //Log.i("Search Test",String.valueOf(info_searchAdapter));
           // Log.i("Search Test",String.valueOf(mList));
            if (resultList != null && resultList.size() > 0){
                search_info_listadapter adapter = new search_info_listadapter(getActivity().getApplicationContext(),resultList);
                // Log.i("Search Test","pass1");
                info_searchAdapter.setAdapter(adapter);
                // Log.i("Search Test","pass2");
                info_searchAdapter.setVisibility(View.VISIBLE);
                info_searchAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Log.i("Search Test", (String)view.getTag());
                        info_search.setQuery((String)view.getTag(),false);


                    }
                });
            }
        } else if (newText.length() == 0 || newText == null) {
            info_searchAdapter.setVisibility(View.GONE);
        }
       // Log.i("Search Test","pass2");
        return true;
    }




}
