package com.example.alfredgao.whut_map;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;

//import layout.infoFragment;
import java.util.List;

import layout.curLocFragment;
import layout.jhFragment;
import layout.mfsFragment;
import layout.nhFragment;
import layout.setFragment;
import layout.yjtFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private LocationClient mLocationClient = null;
    private BDLocationListener myListener;
    private BDLocation location;
    private MyLocationData mLocdata;
    private MapView mMapView = null;
    //private BaiduMap mainMap = null;
    public Fragment myLocFrag = new curLocFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        IntentFilter iFilter = new IntentFilter();
        iFilter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR);
        iFilter.addAction(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR);
        SDKReceiver mReceiver = new SDKReceiver();
        registerReceiver(mReceiver, iFilter);
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                       .setAction("Action", null).show();
//           }
//        });
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.mainLayout, myLocFrag, myLocFrag != null ? myLocFrag.getTag() : null).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

//        mMapView = (MapView) findViewById(R.id.mainmapView);
//        mainMap = mMapView.getMap();
//        mMapView.setVisibility(View.GONE);

        //Location
//        LocationClientOption option = new LocationClientOption();
//        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
//        option.setOpenGps(true);
//        //int span = 3000;
//        //option.setScanSpan(span);
//        //option.setLocationNotify(true);
//        option.setAddrType("all");
//        myListener = new MyLocationListener();
//        mLocationClient = new LocationClient(getApplicationContext());
//        mLocationClient.registerLocationListener(myListener);
//        mLocationClient.setLocOption(option);
//        mLocationClient.start();
        //mLocationClient.requestLocation();





    }


//    public class MyLocationListener implements BDLocationListener {
//        @Override
//        public void onReceiveLocation(BDLocation bdLocation) {
//            System.out.print("GPS 测试");
//            if (bdLocation != null && bdLocation.getLocType() != BDLocation.TypeServerError) {
//                StringBuffer locationLog = new StringBuffer(256);
//                locationLog.append("时间: ");
//                locationLog.append(bdLocation.getTime());
//                locationLog.append("\n错误代码 ");
//                locationLog.append(bdLocation.getLocType());
//                locationLog.append("\n经度: ");
//                locationLog.append(bdLocation.getLatitude());
//                locationLog.append("\n纬度: ");
//                locationLog.append(bdLocation.getLongitude());
//                locationLog.append("\n半径: ");
//                locationLog.append(bdLocation.getRadius());
//                if (bdLocation.getLocType() == bdLocation.TypeGpsLocation) {
//                    locationLog.append("\n速度: ");
//                    locationLog.append(bdLocation.getSpeed());
//                    locationLog.append("\n卫星数目: ");
//                    locationLog.append(bdLocation.getSatelliteNumber());
//                    //locationLog.append(bdLocation.getAltitude());
//                    locationLog.append("\n高度: ");
//                    locationLog.append(bdLocation.getAltitude());
//                    locationLog.append("\n方向: ");
//                    locationLog.append(bdLocation.getDirection());
//                    locationLog.append("\n地址: ");
//                    locationLog.append(bdLocation.getAddress());
//                } else if (bdLocation.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
//                    locationLog.append("\naddr : ");
//                    locationLog.append(bdLocation.getAddrStr());
//                    //运营商信息
//                    locationLog.append("\noperationers : ");
//                    locationLog.append(bdLocation.getOperators());
//                    locationLog.append("\n描述 : ");
//                    locationLog.append("网络定位成功");
//                } else if (bdLocation.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
//                    locationLog.append("\n描述 : ");
//                    locationLog.append("离线定位成功，离线定位结果也是有效的");
//                } else if (bdLocation.getLocType() == BDLocation.TypeServerError) {
//                    locationLog.append("\n描述 : ");
//                    locationLog.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
//                } else if (bdLocation.getLocType() == BDLocation.TypeNetWorkException) {
//                    locationLog.append("\n描述 : ");
//                    locationLog.append("网络不同导致定位失败，请检查网络是否通畅");
//                } else if (bdLocation.getLocType() == BDLocation.TypeCriteriaException) {
//                    locationLog.append("\n描述 : ");
//                    locationLog.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
//                }
//                locationLog.append("\n位置描述 : ");
//                locationLog.append(bdLocation.getLocationDescribe());// 位置语义化信息
//                List<Poi> list = bdLocation.getPoiList();// POI数据
//                if (list != null) {
//                    locationLog.append("\npoilist size = : ");
//                    locationLog.append(list.size());
//                    for (Poi p : list) {
//                        locationLog.append("\npoi= : ");
//                        locationLog.append(p.getId() + " " + p.getName() + " " + p.getRank());
//                    }
//                }
//                String showWeidu = String.valueOf(bdLocation.getLongitude());
//                Toast.makeText(getApplicationContext(),showWeidu , Toast.LENGTH_LONG).show();
//                String showJd = String.valueOf(bdLocation.getLatitude());
//                Toast.makeText(getApplicationContext(), showJd, Toast.LENGTH_LONG).show();
//                Log.i("WHUT_APP_LOC_TEST", locationLog.toString());
//                //myLocFrag.
//                mainMap.setMyLocationEnabled(true);
//                MyLocationData locData = new MyLocationData.Builder().accuracy(bdLocation.getRadius())
//                        .direction(100).latitude(bdLocation.getLatitude())
//                        .longitude(bdLocation.getLongitude()).build();
//                mainMap.setMyLocationData(locData);
//
//
//                MyLocationConfiguration config = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.COMPASS, true, null);
//                mainMap.setMyLocationConfigeration(config);
//                mainMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
//                //mMapView.refreshDrawableState();
//
//            } else {
//                mLocationClient.requestLocation();
//            }
//            //nhFragment.this.location = bdLocation;
//
//
//        }
//    }
     class SDKReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR)){
                //permission error
                Toast.makeText(getApplicationContext(), "验证错误!", Toast.LENGTH_LONG).show();
            }
            if (action.equals(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR)){
                //NetWork
                Toast.makeText(getApplicationContext(), "请检查网络!", Toast.LENGTH_LONG).show();
            }

        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment myFrag = null;
        if (id == R.id.nav_mfs) {
            // Handle the camera action
            myFrag = new mfsFragment();

        } else if (id == R.id.nav_yjt) {
            myFrag = new yjtFragment();

        } else if (id == R.id.nav_nh) {
            myFrag = new nhFragment();
        } else if (id == R.id.nav_infolist) {
            //myFrag = new infoFragment();
        } else if (id == R.id.nav_setting) {
            myFrag = new setFragment();
        } else if (id == R.id.nav_jh) {
            myFrag = new jhFragment();
        } else if (id == R.id.cur_loc) {
            myFrag = new curLocFragment();
        }

        if (myFrag != null) {
            //mMapView.setVisibility(View.GONE);
            //mMapView.refreshDrawableState();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.mainLayout, myFrag, myFrag != null ? myFrag.getTag() : null).commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}


