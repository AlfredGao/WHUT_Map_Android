package com.example.alfredgao.whut_map;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AlfredGao on 5/22/16.
 */
public class mfs_info implements Serializable {
    private double latitude;

    private double longtitude;

    private int imgID;

    private String build_name;

    public int campus_id;
    public boolean isClicked;

    public static List<mfs_info> mfs_info_list = new ArrayList<mfs_info>();

    static {
        mfs_info_list.add(new mfs_info(30.5251180000,114.3536190000,0,"武汉理工大学西区足球场", false,0 ));
        mfs_info_list.add(new mfs_info(30.5280420000,114.3542260000,0,"西院教1楼",false,0 ));
        mfs_info_list.add(new mfs_info(30.5275870000,114.3542620000,0,"资源与环境工程学院",false ,0));
        mfs_info_list.add(new mfs_info(30.5269260000,114.3550430000,0,"武汉理工大学活动中心" ,false,0 ));
        mfs_info_list.add(new mfs_info(30.5264830000,114.3534400000,0,"武汉理工大学网球场" , false,0 ));
        mfs_info_list.add(new mfs_info(30.5289450000,114.3541490000,0,"西院图书馆" , false,0));
        //nh_info_list.add(new mfs_info(30.5153880000,114.3422740000,0,"力学楼" , false ));
        //nh_info_list.add(new nh_info(30.5179400000,114.3405800000,0,"新一教学楼" , false));
        //nh_info_list.add(new nh_info(30.5179400000,114.3405800000,0,"新一教学楼" , false ));
        //nh_info_list.add(new nh_info(30.5179400000,114.3405800000,0,"新一教学楼" , false));
    }

    public mfs_info() {}

    public mfs_info(double latitude, double longtitude, int imgID, String build_name, boolean isClicked,int campus_id)
    {
        super();
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.imgID = imgID;
        this.build_name = build_name;
        this.isClicked = isClicked;
        this.campus_id = campus_id;

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

    public int getCampus_id(){return campus_id;}

}
