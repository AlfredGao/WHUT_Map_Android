package layout;

import java.util.List;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.util.Log;

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



/**
 * A simple {@link Fragment} subclass.
 */
public class infoFragment extends Fragment {


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
        return view;
    }

}
