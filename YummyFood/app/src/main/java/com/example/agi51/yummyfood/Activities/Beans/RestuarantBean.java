package com.example.agi51.yummyfood.Activities.Beans;

import java.io.Serializable;

/**
 * Created by agi51 on 11/3/16.
 */
public class RestuarantBean implements Serializable {

    String menu_name, resturant_name;

    public String getResturant_name(){
        return resturant_name;
    }

    public void setRestuarntName(String resturant_name){
        this.resturant_name = resturant_name;
    }

    public String getMenus(){
        return menu_name;
    }
    public void setMenus(String menu_item){
        this.menu_name=menu_name;
    }


    @Override
    public String toString() {
        return "RestuarantBean{" +
                // "city='" + city + '\'' +
                // ", isChecked=" + isChecked +
                "menu=" + menu_name +
                '}';
    }
}
