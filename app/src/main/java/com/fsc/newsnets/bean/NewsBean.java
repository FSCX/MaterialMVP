package com.fsc.newsnets.bean;

import java.io.Serializable;

/**
 * news实体类，并实现实例化
 */
public class NewsBean implements Serializable{
    private static final long serialVersionUID = 1L;

    //docId
    private String docId;

    //title
    private String title;

    //小内容
    private String digest;

    //图片地址
    private String imgsrc;

    //来源
    private String source;

    //时间
    private String ptime;

    //TAG
    private String tag;

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getPtime() {
        return ptime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}

