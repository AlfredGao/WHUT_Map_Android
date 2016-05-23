package com.example.alfredgao.whut_map;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AlfredGao on 5/22/16.
 */
public class jh_info implements Serializable {
    private double latitude;

    private double longtitude;

    private int imgID;

    private String build_name;

    public boolean isClicked;

    public static List<jh_info> jh_info_list = new ArrayList<jh_info>();

    static {
        jh_info_list.add(new jh_info(30.5187020000,114.3501520000,0,"鉴湖主教学楼", false ));
        jh_info_list.add(new jh_info(30.5187100000,114.3495630000,0,"鉴湖一教学楼",false ));
        jh_info_list.add(new jh_info(30.5184880000,114.3508750000,0,"鉴湖三教学楼",false ));
        jh_info_list.add(new jh_info(30.5196740000,114.3484500000,0,"鉴湖学府超市" ,false ));
        jh_info_list.add(new jh_info(30.5206070000,114.3506190000,0,"学海公寓" , false ));
       // nh_info_list.add(new jh_info(30.5159680000,114.3424080000,0,"理学院" , false));
       // nh_info_list.add(new jh_info(30.5153880000,114.3422740000,0,"力学楼" , false ));
        //nh_info_list.add(new nh_info(30.5179400000,114.3405800000,0,"新一教学楼" , false));
        //nh_info_list.add(new nh_info(30.5179400000,114.3405800000,0,"新一教学楼" , false ));
        //nh_info_list.add(new nh_info(30.5179400000,114.3405800000,0,"新一教学楼" , false));
    }

    public jh_info() {}

    public jh_info(double latitude, double longtitude, int imgID, String build_name, boolean isClicked)
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
