package com.fsc.newsnets.bean;

import java.io.Serializable;

/**
 * 图片实体类
 */
public class ImagesBean implements Serializable{
    private static final long serialVersionUID = 1L;

    //title
    private String title;

    //thumburl
    private String thumburl;

    //source
    private String source;

    //width
    private int width;

    //height
    private int height;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumburl() {
        return thumburl;
    }

    public void setThumburl(String thumburl) {
        this.thumburl = thumburl;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
