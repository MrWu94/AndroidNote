package com.hansheng.deepcopy;

/**
 * Created by hansheng on 2016/7/9.
 */
public class Email {

    private String content;
    private String renwu;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRenwu() {
        return renwu;
    }

    public void setRenwu(String renwu) {
        this.renwu = renwu;
    }

    public Email(String content, String renwu) {

        this.content = content;
        this.renwu = renwu;
    }
}
