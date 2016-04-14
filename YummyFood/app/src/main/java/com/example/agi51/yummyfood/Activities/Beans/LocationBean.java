package com.example.agi51.yummyfood.Activities.Beans;

import java.io.Serializable;

/**
 * Created by agi51 on 3/3/16.
 */
public class LocationBean implements Serializable {

    String city,area;
    //boolean isChecked;
    public String getLocationCity(){
        return city;
    }
    public  void setLocationCity(String city){
        this.city = city;
    }

    public String getSubLocality(){
        return area;
    }
    public void setSubLocality(String area){
        this.area=area;
    }

    /*public boolean isChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }*/

    @Override
    public String toString() {
        return "LocationBean{" +
                "city='" + city + '\'' +
               // ", isChecked=" + isChecked +
                ",subLocality=" + area +
                '}';
    }
}
