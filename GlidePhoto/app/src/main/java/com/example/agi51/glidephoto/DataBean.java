package com.example.agi51.glidephoto;

/**
 * Created by agi51 on 28/1/16.
 */
import java.io.Serializable;

/**
 * Created by rohit on 27/1/16.
 * this is a class which holds the incoming data from server
 * variables should be same as the key in the incoming json string
 */
public class DataBean implements Serializable {
    private String userId,id,title,body;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "DataBean{" +
                "userId='" + userId + '\'' +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
