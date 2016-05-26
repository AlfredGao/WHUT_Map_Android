package layout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

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
import com.example.alfredgao.whut_map.searchResultActivity;



/**
 * A simple {@link Fragment} subclass.
 */
public class infoFragment extends Fragment implements SearchView.OnQueryTextListener {
    private String[] mStrings = {"鉴湖主教学楼",
            "鉴湖一教学楼",
            "鉴湖三教学楼",
            "鉴湖学府超市",
            "学海公寓(鉴湖校区)",
            "武汉理工大学西苑足球场",
            "西苑教1楼",
            "资源与环境工程学院(西苑校区)",
            "武汉理工大学活动中心",
            "武汉理工大学网球场",
            "西苑图书馆",
            "南湖新一教学楼",
            "南湖新二教学楼",
            "南湖新四教学楼",
            "学子苑餐厅（南湖食堂）",
            "南湖博学广场",
            "理学院(南湖校区)",
            "力学楼(南湖校区)"};
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


        //设置信息列表的点击监听事件,点击后跳转至所点击选项的地图位置信息
        info_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("Info ListView Test",String.valueOf(view.getTag()));
                int key = (int)view.getTag();
                List<campus_build_info> list = campus_build_info.campus_build_infoList;
                for(campus_build_info info:list){
                    if (info.getKey() == key){
                        //通过Bundle进行安卓页面之间的参数传递
                        Intent intent = new Intent(getActivity(),info_clickeditem.class);
                        Bundle bundle = new Bundle();

                        //将所点击选项的建筑名称,精度,纬度传递到新的地图页面.
                        String name = info.getBuild_name();
                        Double lat = info.getMarkerLatitude();
                        Double lt = info.getMarkerLongtitude();
                        bundle.putString("BuildName", name);
                        bundle.putDouble("Latitude", lat);
                        bundle.putDouble("Longtitude",lt);

                        intent.putExtras(bundle);
                        //启动页面
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


    //此函数是监听用户时候点击键盘的搜索按键,点击则进行查询算法操作.次函数的参数query为搜索框中进行搜索的文字.
    @Override
    public boolean onQueryTextSubmit(String query) {
        //ArrayList<Integer> queryResult = new ArrayList<>();
        HashMap<String, Double> showResult = new HashMap<>();

        /*搜索排序主要有三步骤:
        *每个建筑名称字符串长度为L1,用户输入的查询字符串长度为L2
        * 1.对于每一个所存储的建筑名称用minDistance函数(内封装Edit Distance算法)计算得到搜索框中的文字与建筑名称的差距度d(最小修改步骤数),
        *   再用公式(L1 - d)/L2计算得到每一个查询结果的权重值queryValue
        *
        * 2.再用adjustKeyWordWeight函数进行权重调整,用来修正模糊查询排序
        *
        * 3.filterQueryValue函数用来筛选符合一定条件的权重值的搜索结果并进行排序返回给用户
        *
        * 排序算法的数据结构为TreeMap 作为一种AVL平衡二叉树, 排序时间复杂度为Olgn*/
        for (String target:mStrings){
            double queryValue = ((double)target.length()-(double)minDistance(query , target))/(double)query.length();
            queryValue = adjustKeyWordWeight(target,query,queryValue);
            filterQueryValue(target,query,queryValue,showResult);
           // queryResult.add(queryValue);
           // Collections.sort(queryResult);
        }


        /*得到搜索结果之后,存入哈希表内并且将值传到搜索结果页面(searchResultActivity)*/
        Intent intent = new Intent(getActivity(),searchResultActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("result_HashMap",showResult);
        intent.putExtras(bundle);
        startActivity(intent);
//        Log.i("Query Test" , String.valueOf(queryResult));
//
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

    /*筛选符合一定范围的权重值的搜索结果,对于用户每个可能输入的查询字符串的长度进行概率估计,确保用户搜索结果的精度*/
    public void filterQueryValue(String target,String query, Double queryValue, HashMap<String, Double> ShowResult){
         int queryLength = query.length();
        if (queryLength <= 1 && queryValue >= 1.0){
            ShowResult.put(target, queryValue);
        } else if (queryLength == 2 && queryValue >= 0.7){
            ShowResult.put(target, queryValue);
        } else if (queryLength == 3 && queryValue >= 0.8) {
            ShowResult.put(target, queryValue);
        } else  if (queryLength == 4 && queryValue >= 0.8) {
            ShowResult.put(target, queryValue);
        } else if (queryLength == 5 && queryValue >= 0.9) {
            ShowResult.put(target, queryValue);
        } else if (queryLength == 6 && queryValue >= 0.9) {
            ShowResult.put(target, queryValue);
        } else if (queryLength > 6 && queryValue >= 0.9) {
            ShowResult.put(target, queryValue);
        }
    }

    /*调整权重,当用户搜索词包含"新","南","鉴","西"等关键词,并且搜索结果包含同样的关键词时增加权重值,使得包含关键字的搜索结果排序靠前
     *当搜索结果全匹配时,调整权重使得结果被精确搜索捕捉器捕捉.*/
    public Double adjustKeyWordWeight(String target, String query,Double queryValue){
        if (containKeyWord(query)){
            String keyWordQueyContain = findQueryKeyWord(query);
            if (target.contains(keyWordQueyContain) && query.length() == target.length() && queryValue >= 1.0) {
                queryValue += 10.0; //精确搜索匹配
            }
            else if (target.contains(keyWordQueyContain)){
                queryValue += 0.2;
            }
        }
        return queryValue;
    }

    public String findQueryKeyWord(String query){
        if (query.contains("西")){
            return "西";
        } else if (query.contains("鉴")) {
            return "鉴";
        } else if(query.contains("南")){
            return "南";
        } else if (query.contains("新")){
            return "新";
        }
        return null;
    }

    public boolean containKeyWord(String s){
        return s.contains("西") || s.contains("鉴") || s.contains("南") || s.contains("新");
    }



    @Override
    public boolean onQueryTextChange(String newText) {
        Log.i("Search Test",newText);

        if (newText.length() > 0){
            /*初始化自动补全数据结构字典树*/
            Trie trie = new Trie();
            List<String> mList = new ArrayList<>();
            List<String> resultList = new ArrayList<>();
            /*将所有结果添加到字典树之内*/
            for(String s:mStrings){
                mList.add(s);
                trie.insert(s);
            }

            TrieNode tNode = trie.searchNode(newText);
            if (tNode != null) {
                /*获取所有与用户输入文字前缀相同的结果并存入数组中*/
                resultList = trie.showSamePrefix(tNode);

                /*调整显示格式*/
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

            /*对于自动补全的列表设置点击事件响应,点击后搜索框自动补全搜索文字*/
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
