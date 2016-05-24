package layout;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
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
import com.example.alfredgao.whut_map.mfs_info;
import com.example.alfredgao.whut_map.nh_info;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class mfsFragment extends Fragment {

    private MapView mapView = null;

    final List<mfs_info> mfs_maker_list = mfs_info.mfs_info_list;

    final List<Marker> icon_list = new ArrayList<>();

    final HashMap<mfs_info,Marker> info_maker_hs = new HashMap<>();

    private AutoCompleteTextView AutoCView = null;

    private View map;
    public mfsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        map = inflater.inflate(R.layout.fragment_mfs, null);
        mapView = (MapView)map.findViewById(R.id.mfsmapView);
        final BaiduMap BMap = mapView.getMap();
        final LatLng ne = new LatLng(30.5313980000,114.3641740000);
        final LatLng sw = new LatLng(30.5203550000,114.3503050000);
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
        for (final mfs_info info: mfs_maker_list) {
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
                    mfs_info info_temp = (mfs_info)marker.getExtraInfo().get(String.valueOf(info));
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
        AutoCView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("auto com test", String.valueOf(position));
                TextView textView = (TextView) view;
                String show = String.valueOf(textView.getText());
                Log.i("auto com test", show);
                for (mfs_info info: mfs_maker_list){
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
                        hideKeyboard();

                    }
                }
            }
        });}

    private void hideKeyboard(){
        InputMethodManager imm = (InputMethodManager)getActivity()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView()
                .getWindowToken(), 0);
    }

    private void setAutoComplete(){
        String[] autoCompleteAdapter = new String[] {
                "武汉理工大学西区足球场",
                "西院教1楼",
                "资源与环境工程学院",
                "武汉理工大学活动中心",
                "武汉理工大学网球场",
                "西院图书馆"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                R.layout.autolist,
                autoCompleteAdapter);
        AutoCView = (AutoCompleteTextView)map.findViewById(R.id.mfsAutoComplete);
        AutoCView.setAdapter(adapter);
    }




    private void keepOtherMarkerColor(Marker marker){
        for(Marker changColorMarker: icon_list) {
            if (changColorMarker != marker){
                changColorMarker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.marker));
            }
        }
    }
}
