package layout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.example.alfredgao.whut_map.R;

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
        final LatLng ne = new LatLng(30.5180520000,114.3468550000);
        final LatLng sw = new LatLng(30.5078470000,114.3336680000);
        BMap.setOnMapLoadedCallback(new BaiduMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                BMap.setMapStatusLimits(new LatLngBounds.Builder().include(ne).include(sw).build());
            }
        });

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
