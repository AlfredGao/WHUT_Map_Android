package com.example.alfredgao.whut_map;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by AlfredGao on 5/22/16.
 */
public class nh_info implements Serializable {
    private double latitude;

    private double longtitude;

    private int imgID;

    private String build_name;

    public boolean isClicked;

    public static List<nh_info> nh_info_list = new ArrayList<nh_info>();

    static {
        nh_info_list.add(new nh_info(30.5179400000,114.3405800000,0,"新一教学楼", false ));
        nh_info_list.add(new nh_info(30.5175240000,114.3417170000,0,"新二教学楼",false ));
        nh_info_list.add(new nh_info(30.5185310000,114.3407690000,0,"新四教学楼",false ));
        nh_info_list.add(new nh_info(30.5169670000,114.3358820000,0,"学子苑餐厅（南湖食堂）" ,false ));
        //nh_info_list.add(new nh_info(30.5179400000,114.3405800000,0,"新一教学楼" ));
        //nh_info_list.add(new nh_info(30.5179400000,114.3405800000,0,"新一教学楼" ));
    }

    public nh_info() {}

    public nh_info(double latitude, double longtitude, int imgID, String build_name,boolean isClicked)
    {
        super();
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.imgID = imgID;
        this.build_name = build_name;
        this.isClicked = isClicked;
    }

    public double getMarkerLatitude() {
        return latitude;
    }

    public double getMarkerLongtitude() {
        return longtitude;
    }

    public int getImgID() {
        return imgID;
    }

    public String getBuild_name() {
        return build_name;
    }

    public boolean getIsClicked() {
        return isClicked;
    }
}
