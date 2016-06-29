package com.hansheng.studynote.xml;

import java.io.InputStream;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by hansheng on 2016/6/26.
 */
public interface BookParser {


    public List<Book> parse(InputStream is)throws Exception;

    public String serialize(List<Book> books)throws Exception;

}
