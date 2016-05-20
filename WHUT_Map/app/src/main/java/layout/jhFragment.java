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
public class jhFragment extends Fragment {


    public jhFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View map = inflater.inflate(R.layout.fragment_jh, null);
        MapView mapView = (MapView)map.findViewById(R.id.jhmapView);
        final BaiduMap BMap = mapView.getMap();
        final LatLng ne = new LatLng(30.5219880000,114.3523440000);
        final LatLng sw = new LatLng(30.5174460000,114.3455520000);
        BMap.setOnMapLoadedCallback(new BaiduMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                BMap.setMapStatusLimits(new LatLngBounds.Builder().include(ne).include(sw).build());
            }
        });
        return map;
    }

}
