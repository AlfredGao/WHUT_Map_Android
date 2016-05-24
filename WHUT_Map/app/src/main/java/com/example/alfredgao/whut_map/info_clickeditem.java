package com.example.alfredgao.whut_map;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import android.util.Log;
import android.widget.TextView;

public class info_clickeditem extends AppCompatActivity {
    private MapView mMapView = null;
    private LatLng latitude;
    private LatLng longtitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_info_clickeditem);
        mMapView = (MapView) findViewById(R.id.info_clickedmapView);
        BaiduMap BMap = mMapView.getMap();

        Bundle bundle = this.getIntent().getExtras();

        String name = bundle.getString("BuildName");
        Double lat = bundle.getDouble("Latitude");
        Double lt = bundle.getDouble("Longtitude");
        LatLng point1 = new LatLng(lat, lt);
        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.marker);
        OverlayOptions options = new MarkerOptions().position(point1).icon(bitmap);
        Marker marker = (Marker)(BMap.addOverlay(options));

        InfoWindow mInfoWindow;
        TextView locationView = new TextView(getApplicationContext());
        locationView.setPadding(30,20,30,30);
        locationView.setText(name);
        LatLng pt = marker.getPosition();
        mInfoWindow = new InfoWindow(locationView, pt, -47);
        BMap.showInfoWindow(mInfoWindow);

        MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(point1);
        BMap.animateMapStatus(msu);
        BMap.setMaxAndMinZoomLevel(21,18);



//        BMap.setMyLocationEnabled(true);
//        MyLocationData locData = new MyLocationData.Builder().accuracy(100)
//                .direction(0).latitude(lat)
//                .longitude(lt).build();
//        BMap.setMyLocationData(locData);
//
//
//        MyLocationConfiguration config = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.COMPASS, true, bitmap);
//        BMap.setMyLocationConfigeration(config);
        Log.i("Clicked test",name);
        Log.i("Clicked test",String.valueOf(lat));
        Log.i("Clicked test",String.valueOf(lt));



    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override

    protected void onResume(){
        super.onResume();
        mMapView.onResume();
    }

    @Override

    protected void onPause(){
        super.onPause();
        mMapView.onPause();
    }
}
