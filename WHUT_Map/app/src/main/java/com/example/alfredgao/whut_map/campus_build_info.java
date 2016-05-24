package com.example.alfredgao.whut_map;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AlfredGao on 5/23/16.
 */
public class campus_build_info implements Serializable {
    private int key;

    private double latitude;

    private double longtitude;

    private int imgID;

    private String build_name;

    public String campus_name;

    public int isClicked_num;

    public static List<campus_build_info> campus_build_infoList = new ArrayList<campus_build_info>();

    static {
        campus_build_infoList.add(new campus_build_info(17,30.5187020000,114.3501520000,0,"鉴湖主教学楼", 0 ,"鉴湖校区"));
        campus_build_infoList.add(new campus_build_info(18,30.5187100000,114.3495630000,0,"鉴湖一教学楼",0 ,"鉴湖校区"));
        campus_build_infoList.add(new campus_build_info(19,30.5184880000,114.3508750000,0,"鉴湖三教学楼",0 ,"鉴湖校区"));
        campus_build_infoList.add(new campus_build_info(20,30.5196740000,114.3484500000,0,"鉴湖学府超市" ,0 ,"鉴湖校区"));
        campus_build_infoList.add(new campus_build_info(21,30.5206070000,114.3506190000,0,"学海公寓" , 0,"鉴湖校区" ));
        campus_build_infoList.add(new campus_build_info(24,30.5251180000,114.3536190000,0,"武汉理工大学西区足球场", 0,"鉴湖校区" ));
        campus_build_infoList.add(new campus_build_info(25,30.5280420000,114.3542260000,0,"西院教1楼",0,"西苑" ));
        campus_build_infoList.add(new campus_build_info(26,30.5275870000,114.3542620000,0,"资源与环境工程学院",0 ,"西苑"));
        campus_build_infoList.add(new campus_build_info(27,30.5269260000,114.3550430000,0,"武汉理工大学活动中心" ,0,"西苑" ));
        campus_build_infoList.add(new campus_build_info(28,30.5264830000,114.3534400000,0,"武汉理工大学网球场" , 0,"西苑" ));
        campus_build_infoList.add(new campus_build_info(29,30.5289450000,114.3541490000,0,"西院图书馆" , 0,"西苑"));
        campus_build_infoList.add(new campus_build_info(1,30.5179400000,114.3405800000,0,"新一教学楼", 0,"南湖新校区" ));
        campus_build_infoList.add(new campus_build_info(2,30.5175240000,114.3417170000,0,"新二教学楼",0,"南湖新校区"));
        campus_build_infoList.add(new campus_build_info(3,30.5185310000,114.3407690000,0,"新四教学楼",0 ,"南湖新校区"));
        campus_build_infoList.add(new campus_build_info(4,30.5169670000,114.3358820000,0,"学子苑餐厅（南湖食堂）" ,0 ,"南湖新校区"));
        campus_build_infoList.add(new campus_build_info(5,30.5173210000,114.3404010000,0,"博学广场" , 0,"南湖新校区"));
        campus_build_infoList.add(new campus_build_info(6,30.5159680000,114.3424080000,0,"理学院" , 0,"南湖新校区"));
        campus_build_infoList.add(new campus_build_info(7,30.5153880000,114.3422740000,0,"力学楼" , 0 ,"南湖新校区"));
        // nh_info_list.add(new jh_info(30.5159680000,114.3424080000,0,"理学院" , false));
        // nh_info_list.add(new jh_info(30.5153880000,114.3422740000,0,"力学楼" , false ));
        //nh_info_list.add(new nh_info(30.5179400000,114.3405800000,0,"新一教学楼" , false));
        //nh_info_list.add(new nh_info(30.5179400000,114.3405800000,0,"新一教学楼" , false ));
        //nh_info_list.add(new nh_info(30.5179400000,114.3405800000,0,"新一教学楼" , false));
    }

    public campus_build_info() {}

    public campus_build_info(int key,double latitude, double longtitude, int imgID, String build_name, int isClicked_num,String campus_name)
    {
        super();
        this.key = key;
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.imgID = imgID;
        this.build_name = build_name;
        this.isClicked_num = isClicked_num;
        this.campus_name = campus_name;
    }
    public int getKey(){ return key;}

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

    public String getCampus_id() {return campus_name; }

    public int getIsClicked() {
        return isClicked_num;
    }


}
