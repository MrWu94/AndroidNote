package com.hansheng.studynote.SQLDataBase.search;

/**
 * Created by hansheng on 17-1-19.
 */

public class BookSave {

    /**
     * id 主键，自增
     */
    private int id;



    /**
     * 搜索的书名
     */
    private String bookName;
    /**
     * 搜索的页码
     */
    private int bookNumber;
    /**
     * 背景
     */

    private String background;

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getBookNumber() {
        return bookNumber;
    }

    public void setBookNumber(int bookNumber) {
        this.bookNumber = bookNumber;
    }
}
