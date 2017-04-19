package com.hansheng.studynote.Listview.RecyclerView;

import java.util.List;

/**
 * Created by hansheng on 16-12-12.
 */

public class News {
    private String title;
    private List<String> imageUrl;

    public List<String> getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(List<String> imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }




}
