package com.example.agi51.navigationdrw;

import java.io.Serializable;

/**
 * Created by agi51 on 6/2/16.
 */
public class Beam implements Serializable {

    private String title;
    private String id;

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;

    }

    public String getThumbnail(){
        return id;
    }

    public void setThumbnail(String thumbnail){
        this.id = id;
    }

    @Override
    public String toString() {
        return "Beam{"+
                "title="+title+"\n"+
                "thumbnail="+id+"\n"+
                "}";
    }
}
