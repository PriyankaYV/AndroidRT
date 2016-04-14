package com.example.agi51.yummyfood.Activities.Beans;

import java.io.Serializable;

/**
 * Created by agi51 on 29/2/16.
 */
public class DataBean implements Serializable {

    private String city, area,rest_name, rest_rating, logo, gmailName,region, subRegion;

    public String getRegion(){
        return region;
    }
    public void setRegion(String region){
        this.region = region;
    }

    public String getSubRegion() {
        return subRegion;
    }

    public void setSubRegion(String subRegion) {
        this.subRegion = subRegion;
    }

    public String getGmailName(){
        return gmailName;
    }
    public void setGmailName(String gmailName){
        this.gmailName = gmailName;
    }

    public String getCity(){
        return city;
    }
    public  void setCity(String city){
        this.city = city;
    }

    public String getArea(){
        return area;
    }
    public  void setArea(String area){
        this.area = area;
    }



    public String getRest_name() {
        return rest_name;
    }

    public void setRest_name(String rest_name) {
        this.rest_name = rest_name;
    }

    public String getRest_rating() {
        return rest_rating;
    }

    public void setRest_rating(String rest_rating) {
        this.rest_rating = rest_rating;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }


    @Override
    public String toString() {
        return "DataBean{" +
                "Title'" + rest_name + '\'' +
                ", Rating='" + rest_rating+ '\'' +
                ", logo='" + logo + '\'' +
                ",city="+city+"\n"+
                ",place="+area+"\n"+
                '}';
    }

}
