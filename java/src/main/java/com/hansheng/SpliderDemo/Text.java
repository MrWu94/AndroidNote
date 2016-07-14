package com.hansheng.SpliderDemo;

import java.util.Iterator;
import java.util.List;

/**
 * Created by hansheng on 2016/7/14.
 */
public class Text {

    public static void main(String... args) {

        Rule rule = new Rule("http://www1.sxcredit.gov.cn/public/infocomquery.do?method=publicIndexQuery", new String[]{"query.enterprisename", "query.registationnumber"}, new String[]{"兴网", ""}, "cont_right", 0, 1);
        List extracts = ExtractService.extract(rule);
        printf(extracts);

        Rule rule1 = new Rule("http://www.11315.com/search", new String[]{"name"}, new String[]{"兴网"}, "div.g-mn div.con-model", 2, 0);
        List extracts1 = ExtractService.extract(rule1);
        printf(extracts1);

        Rule rule2= new Rule("http://news.baidu.com/ns",
                new String[] { "word" }, new String[] { "支付宝" },
                null, -1, Rule.GET);
        List<LinkTypeData> extracts2 = ExtractService.extract(rule2);
        printf(extracts2);


    }

    public static void printf(List<LinkTypeData> datas) {
        Iterator var3 = datas.iterator();

        while (var3.hasNext()) {
            LinkTypeData data = (LinkTypeData) var3.next();
            System.out.println(data.getLinkText());
            System.out.println(data.getLinkHref());
            System.out.println("***********************************");
        }

    }

}
