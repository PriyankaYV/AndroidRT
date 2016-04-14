package com.example.agi51.hmkcloud;

/**
 * Created by agi51 on 5/2/16.
 */
import java.io.Serializable;
import java.net.URL;

/**
 * Created by rohit on 27/1/16.
 * this is a class which holds the incoming data from server
 * variables should be same as the key in the incoming json string
 */
public class DataBean implements Serializable {
    private String albumId,id,title,url;


    public String getUserId() {
        return albumId;
    }

    public void setUserId(String userId) {
        this.albumId = albumId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "DataBean{" +
                "userId='" + albumId + '\'' +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", body='" + url + '\'' +
                '}';
    }
}
