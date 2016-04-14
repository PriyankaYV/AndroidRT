package com.example.agi51.yummyfood.Activities.Beans;

import java.io.Serializable;

/**
 * Created by agi51 on 9/3/16.
 */
public class AreaBean implements Serializable {

    String rest_locality;

    public String getSubLocality(){
        return rest_locality;
    }
    public void setSubLocality(String area){
        this.rest_locality=rest_locality;
    }


    @Override
    public String toString() {
        return "AreaBean{" +
               // "city='" + city + '\'' +
                // ", isChecked=" + isChecked +
                "subLocality=" + rest_locality +
                '}';
    }
}
