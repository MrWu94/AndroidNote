// BookManager.aidl
package com.hansheng.studynote.ui.service;
import com.hansheng.studynote.ui.service.Book;

// Declare any non-default types here with import statements

interface BookManager {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
     //所有的返回值前都不需要加任何东西，不管是什么数据类型
        List<Book> getBooks();

        //传参时除了Java基本类型以及String，CharSequence之外的类型
        //都需要在前面加上定向tag，具体加什么量需而定
        void addBook(in Book book);

}
