package layout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.example.alfredgao.whut_map.R;
import com.example.alfredgao.whut_map.jh_info;
import com.example.alfredgao.whut_map.nh_info;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class jhFragment extends Fragment {

    private MapView mapView = null;

    final List<jh_info> jh_maker_list = jh_info.jh_info_list;

    final List<Marker> icon_list = new ArrayList<>();

    final HashMap<jh_info,Marker> info_maker_hs = new HashMap<>();

    private AutoCompleteTextView nhAutoCView = null;

    private View map;

    public jhFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        map = inflater.inflate(R.layout.fragment_jh, null);
        mapView = (MapView)map.findViewById(R.id.jhmapView);
        final BaiduMap BMap = mapView.getMap();
        final LatLng ne = new LatLng(30.5219880000,114.3523440000);
        final LatLng sw = new LatLng(30.5174460000,114.3455520000);
        BMap.setOnMapLoadedCallback(new BaiduMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                BMap.setMapStatusLimits(new LatLngBounds.Builder().include(ne).include(sw).build());
            }
        });

        setIconMarkerOnMap(BMap);
        setAutoComplete();
        setAutoCompleteListener(BMap);

        return map;
    }
    private void setIconMarkerOnMap(final BaiduMap BMap){
        for (final jh_info info: jh_maker_list) {
            LatLng point1 = new LatLng(info.getMarkerLatitude(), info.getMarkerLongtitude());
            BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.marker);
            OverlayOptions options = new MarkerOptions().position(point1).icon(bitmap);
            Marker marker = (Marker)(BMap.addOverlay(options));
            info_maker_hs.put(info,marker);
            icon_list.add(marker);
            Bundle bundle = new Bundle();
            bundle.putSerializable(String.valueOf(info), info);
            marker.setExtraInfo(bundle);
            BMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(final Marker marker) {
                    //return false;
                    jh_info info_temp = (jh_info)marker.getExtraInfo().get(String.valueOf(info));
                    Log.i("NH INFO TEST", String.valueOf(info_temp));
                    Log.i("NH INFO TEST",String.valueOf(marker));
                    InfoWindow mInfoWindow;
                    if (info_temp != null) {
                        TextView locationView = new TextView(getActivity().getApplicationContext());
                        locationView.setPadding(30,20,30,30);
                        locationView.setText(info.getBuild_name());
                        marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.markerclicked));
                        keepOtherMarkerColor(marker);
                        LatLng pt = marker.getPosition();
                        mInfoWindow = new InfoWindow(locationView, pt, -47);
                        BMap.showInfoWindow(mInfoWindow);
                    }
                    return true;
                }
            });
        }
    }


    private void setAutoCompleteListener(final BaiduMap BMap){
        nhAutoCView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("auto com test", String.valueOf(position));
                TextView textView = (TextView) view;
                String show = String.valueOf(textView.getText());
                Log.i("auto com test", show);
                for (jh_info info: jh_maker_list){
                    if (info.getBuild_name() == show){
                        InfoWindow selectWindow;
                        TextView selectLocationView = new TextView(getActivity().getApplicationContext());
                        selectLocationView.setPadding(30,20,30,30);
                        selectLocationView.setText(show);
                        Marker marker = info_maker_hs.get(info);
                        marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.markerclicked));
                        keepOtherMarkerColor(marker);
                        LatLng pt = marker.getPosition();
                        selectWindow = new InfoWindow(selectLocationView, pt, -47);
                        BMap.showInfoWindow(selectWindow);

                    }
                }
            }
        });}



    private void setAutoComplete(){
        String[] autoCompleteAdapter = new String[] {
                "鉴湖主教学楼",
                "鉴湖一教学楼",
                "鉴湖三教学楼",
                "鉴湖学府超市",
                "学海公寓"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                R.layout.autolist,
                autoCompleteAdapter);
        nhAutoCView = (AutoCompleteTextView)map.findViewById(R.id.jhAutoComplete);
        nhAutoCView.setAdapter(adapter);
    }




    private void keepOtherMarkerColor(Marker marker){
        for(Marker changColorMarker: icon_list) {
            if (changColorMarker != marker){
                changColorMarker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.marker));
            }
        }
    }

}
