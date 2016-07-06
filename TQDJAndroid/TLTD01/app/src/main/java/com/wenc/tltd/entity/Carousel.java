package com.wenc.tltd.entity;

/**
 * 轮播图实体类
 * Created by WenC on 2016-06-17.
 */
public class Carousel {

    private int id;
    private String url;

    public Carousel(){

    }

    public Carousel(int id, String url){
        this.id = id;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Carousel{" +
                "id=" + id +
                ", url='" + url + '\'' +
                '}';
    }
}
