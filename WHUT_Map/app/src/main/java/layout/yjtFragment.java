package layout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.example.alfredgao.whut_map.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class yjtFragment extends Fragment {


    public yjtFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View map = inflater.inflate(R.layout.fragment_yjt, null);
        MapView mapView = (MapView)map.findViewById(R.id.yjtmapView);
        final BaiduMap BMap = mapView.getMap();
        final LatLng ne = new LatLng(30.6184580000,114.3690970000);
        final LatLng sw = new LatLng(30.6075490000,114.3559280000);
        BMap.setOnMapLoadedCallback(new BaiduMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                BMap.setMapStatusLimits(new LatLngBounds.Builder().include(ne).include(sw).build());
            }
        });
        return map;
    }

}
