package layout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.example.alfredgao.whut_map.MainActivity;
import com.example.alfredgao.whut_map.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class curLocFragment extends Fragment {

    public BaiduMap curLocMap;
    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();
    private BaiduMap BMap = null;
    public curLocFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View map = inflater.inflate(R.layout.fragment_cur_loc, null);
        MapView mapView = (MapView)map.findViewById(R.id.myLocmapView);
        BMap = mapView.getMap();
        mLocationClient = new LocationClient(getActivity().getApplicationContext());
        mLocationClient.registerLocationListener(myListener);
        mLocationClient.start();
        setClientOption();

        return map;
    }
    public void setClientOption() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setOpenGps(true);
        option.setIsNeedAddress(true);
        option.setIsNeedLocationDescribe(true);
        mLocationClient.setLocOption(option);
    }

    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            if (bdLocation != null && bdLocation.getLocType() != BDLocation.TypeServerError) {
                StringBuffer locationLog = new StringBuffer(256);
                locationLog.append("时间: ");
                locationLog.append(bdLocation.getTime());
                locationLog.append("\n错误代码 ");
                locationLog.append(bdLocation.getLocType());
                locationLog.append("\n经度: ");
                locationLog.append(bdLocation.getLatitude());
                locationLog.append("\n纬度: ");
                locationLog.append(bdLocation.getLongitude());
                locationLog.append("\n半径: ");
                locationLog.append(bdLocation.getRadius());
                if (bdLocation.getLocType() == bdLocation.TypeGpsLocation) {
                    locationLog.append("\n速度: ");
                    locationLog.append(bdLocation.getSpeed());
                    locationLog.append("\n卫星数目: ");
                    locationLog.append(bdLocation.getSatelliteNumber());
                    //locationLog.append(bdLocation.getAltitude());
                    locationLog.append("\n高度: ");
                    locationLog.append(bdLocation.getAltitude());
                    locationLog.append("\n方向: ");
                    locationLog.append(bdLocation.getDirection());
                    locationLog.append("\n地址: ");
                    locationLog.append(bdLocation.getAddress());
                } else if (bdLocation.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                    locationLog.append("\naddr : ");
                    locationLog.append(bdLocation.getAddrStr());
                    //运营商信息
                    locationLog.append("\noperationers : ");
                    locationLog.append(bdLocation.getOperators());
                    locationLog.append("\n描述 : ");
                    locationLog.append("网络定位成功");
                } else if (bdLocation.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
                    locationLog.append("\n描述 : ");
                    locationLog.append("离线定位成功，离线定位结果也是有效的");
                } else if (bdLocation.getLocType() == BDLocation.TypeServerError) {
                    locationLog.append("\n描述 : ");
                    locationLog.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
                } else if (bdLocation.getLocType() == BDLocation.TypeNetWorkException) {
                    locationLog.append("\n描述 : ");
                    locationLog.append("网络不同导致定位失败，请检查网络是否通畅");
                } else if (bdLocation.getLocType() == BDLocation.TypeCriteriaException) {
                    locationLog.append("\n描述 : ");
                    locationLog.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
                }
                locationLog.append("\n位置描述 : ");
                locationLog.append(bdLocation.getLocationDescribe());// 位置语义化信息
                List<Poi> list = bdLocation.getPoiList();// POI数据
                if (list != null) {
                    locationLog.append("\npoilist size = : ");
                    locationLog.append(list.size());
                    for (Poi p : list) {
                        locationLog.append("\npoi= : ");
                        locationLog.append(p.getId() + " " + p.getName() + " " + p.getRank());
                    }
                }
                String showWeidu = String.valueOf(bdLocation.getLongitude());
                Toast.makeText(getActivity().getApplicationContext(),showWeidu , Toast.LENGTH_LONG).show();
                String showJd = String.valueOf(bdLocation.getLatitude());
                Toast.makeText(getActivity().getApplicationContext(), showJd, Toast.LENGTH_LONG).show();
                Log.i("WHUT_APP_LOC_TEST", locationLog.toString());
                //myLocFrag.
                BMap.setMyLocationEnabled(true);
                MyLocationData locData = new MyLocationData.Builder().accuracy(bdLocation.getRadius())
                        .direction(100).latitude(bdLocation.getLatitude())
                        .longitude(bdLocation.getLongitude()).build();
                BMap.setMyLocationData(locData);


                MyLocationConfiguration config = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.COMPASS, true, null);
                BMap.setMyLocationConfigeration(config);
                //BMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
                //mMapView.refreshDrawableState();

            } else {
                mLocationClient.requestLocation();
            }

        }
    }

}
