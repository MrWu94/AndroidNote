package com.hansheng.SpliderDemo;

/**
 * Created by hansheng on 2016/7/14.
 */
public class Rule {
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String[] getParams() {
        return params;
    }

    public void setParams(String[] params) {
        this.params = params;
    }

    public String[] getValues() {
        return values;
    }

    public void setValues(String[] values) {
        this.values = values;
    }

    public String getRequestTagName() {
        return requestTagName;
    }

    public void setRequestTagName(String requestTagName) {
        this.requestTagName = requestTagName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(int requestMethod) {
        this.requestMethod = requestMethod;
    }

    private String[] params;

    private String[] values;

    private String requestTagName;

    private int type = ID;

    private int requestMethod = GET;

    public final static int GET=0;
    public final static int POST=1;

    public final static int CLASS = 0;
    public final static int ID = 1;
    public final static int SELECTION = 2;

    public Rule() {
    }

    public Rule(String url, String[] params, String[] values, String resultTagName, int type, int requestMethod) {
        super();
        this.url=url;
        this.params=params;
        this.values=values;
        this.requestTagName=resultTagName;
        this.type=type;
        this.requestMethod=requestMethod;
    }
}
