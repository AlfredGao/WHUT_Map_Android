package layout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.model.inner.Point;
import com.example.alfredgao.whut_map.R;
import com.example.alfredgao.whut_map.nh_info;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class nhFragment extends Fragment {


    private MapView mapView = null;

    public nhFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View map = inflater.inflate(R.layout.fragment_nh, null);
        mapView = (MapView)map.findViewById(R.id.nhmapView);
        final BaiduMap BMap = mapView.getMap();
        final LatLng ne = new LatLng(30.5203860000,114.3467650000);
        final LatLng sw = new LatLng(30.5078470000,114.3336680000);
        BMap.setOnMapLoadedCallback(new BaiduMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                BMap.setMapStatusLimits(new LatLngBounds.Builder().include(ne).include(sw).build());
            }
        });


//        LatLng point = new LatLng(30.5179400000,114.3405800000);
//        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.marker);
//        OverlayOptions options = new MarkerOptions().position(point).icon(bitmap);
//        Marker marker = (Marker)(BMap.addOverlay(options));
//        BMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
//            @Override
//            public boolean onMarkerClick(Marker marker) {
//                return false;
//            }
//        });

        List<nh_info> nh_maker_list = nh_info.nh_info_list;
        final List<Marker> icon_list = new ArrayList<>();
        for (final nh_info info: nh_maker_list) {
            LatLng point1 = new LatLng(info.getMarkerLatitude(), info.getMarkerLongtitude());
            BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.marker);
            OverlayOptions options = new MarkerOptions().position(point1).icon(bitmap);
            Marker marker = (Marker)(BMap.addOverlay(options));
            icon_list.add(marker);
            Bundle bundle = new Bundle();
            bundle.putSerializable(String.valueOf(info), info);
            marker.setExtraInfo(bundle);
            BMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(final Marker marker) {
                    //return false;
                    nh_info info_temp = (nh_info)marker.getExtraInfo().get(String.valueOf(info));
                    Log.i("NH INFO TEST", String.valueOf(info_temp));
                    Log.i("NH INFO TEST",String.valueOf(marker));
                    InfoWindow mInfoWindow;
                    if (info_temp != null) {
                        TextView locationView = new TextView(getActivity().getApplicationContext());
                        locationView.setPadding(30,20,30,30);
                        locationView.setText(info.getBuild_name());

                        //android.graphics.Point p = BMap.getProjection().toScreenLocation(marker.getPosition());
                        //p.y -= 47;
                        marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.markerclicked));
                        for(Marker changColorMarker: icon_list) {
                            if (changColorMarker != marker){
                                changColorMarker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.marker));
                            }
                        }
                        LatLng pt = marker.getPosition();
                        mInfoWindow = new InfoWindow(locationView, pt, -47);
                        BMap.showInfoWindow(mInfoWindow);
                    }



                    return true;
                }
            });

        }


//        BMap.setMyLocationEnabled(true);
//        mLocdata = new MyLocationData.Builder().accuracy(bdLocation.getRadius()).
//                latitude(bdLocation.getLatitude())
//                .longitude(bdLocation.getLongitude()).build();
//        BMap.setMyLocationData(mLocdata);
//        BitmapDescriptor locMarker = BitmapDescriptorFactory
//                .fromResource(R.drawable.ic_menu_send);
//        MyLocationConfiguration.LocationMode Cong_Mode = MyLocationConfiguration.LocationMode.NORMAL;
//        MyLocationConfiguration config = new MyLocationConfiguration(Cong_Mode, true, locMarker);
//        BMap.setMyLocationConfigeration(config);
//
//        mLocationClient = new LocationClient(getContext());
//        mLocationClient.registerLocationListener(myListener);
//        mLocationClient.start();
//        mLocationClient.requestLocation();
//        LocationClientOption option = new LocationClientOption();
//        option.setLocationMode(LocationClientOption.LocationMode.Device_Sensors);
//        option.setOpenGps(true);
//        mLocationClient.setLocOption(option);
        //BDLocation nhLocation = mLocationClient.getLastKnownLocation();
        //System.out.println(nhLocation);


        return map;
    }







}
