package com.example.agi51.task1;

import java.io.Serializable;

/**
 * Created by agi51 on 27/1/16.
 */
public class DataBean implements Serializable {

    private String userId,id,title,name;

    public String getUserId(){
        return userId;
    }
    public void setUserId(String userId){
        this.userId = userId;
    }
    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id =id;
    }
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title=title;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "DataBean{" +
                "userId="+ userId +'\n'+
                ",id="+id + '\n'+
                ",title="+title+ '\n'+
                ",name="+name+'\n'+
                "}";
    }
}
