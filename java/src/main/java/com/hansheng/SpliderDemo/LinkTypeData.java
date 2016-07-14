package com.hansheng.SpliderDemo;

/**
 * Created by hansheng on 2016/7/14.
 */
public class LinkTypeData {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLinkHref() {
        return linkHref;
    }

    public void setLinkHref(String linkHref) {
        this.linkHref = linkHref;
    }

    public String getLinkText() {
        return linkText;
    }

    public void setLinkText(String linkText) {
        this.linkText = linkText;
    }

    public String getSumnmary() {
        return sumnmary;
    }

    public void setSumnmary(String sumnmary) {
        this.sumnmary = sumnmary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private int id;
    private String linkHref;
    private String linkText;
    private String sumnmary;

    private String content;
}
