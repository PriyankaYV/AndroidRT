package com.rohit.myapplication;

import java.io.Serializable;
import java.net.URL;

/**
 * Created by rohit on 27/1/16.
 * this is a class which holds the incoming data from server
 * variables should be same as the key in the incoming json string
 */
public class DataBean implements Serializable {
    private String Date,time,user_id,user_type,ip;


    public String getDiscount() {
        return Date;
    }

    public void setDiscount(String Date) {
        this.Date= Date;
    }

   public String getForm_date() {
        return time;
    }

    public void setForm_date(String time) {
        this.time=time;
    }


    public String getTo_date() {
        return user_id;
    }

    public void setTo_date(String user_id) {
        this.user_id= user_id;
    }

    public String getUsertype() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getActivity() {
        return ip;
    }

    public void setActivity(String ip) {
        this.ip = ip;
    }

/*
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip= ip;
    }


*/

    @Override
    public String toString() {
        return "DataBean{" +
                "Discount='" + Date+ '\'' +
               ",Form_date='" + time+ '\'' +
               ", To_date='" + user_id+ '\'' +
                ", Contact_number='" + user_type + '\'' +
                ",EmailId=" +ip+"\n" +
               //"Locality=" + ip+ "\n"+
                "}";
    }
}
