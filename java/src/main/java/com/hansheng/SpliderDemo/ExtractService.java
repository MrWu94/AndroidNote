package com.hansheng.SpliderDemo;

import com.hansheng.SpliderDemo.exception.RuleException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by hansheng on 2016/7/14.
 */
public class ExtractService {
    public ExtractService() {
    }

    public static List<LinkTypeData> extract(Rule rule) {
        validateRule(rule);
        ArrayList datas = new ArrayList();
        LinkTypeData data = null;

        try {
            String e = rule.getUrl();
            String[] params = rule.getParams();
            String[] values = rule.getValues();
            String resultTagName = rule.getRequestTagName();
            int type = rule.getType();
            int requestType = rule.getRequestMethod();
            Connection conn = Jsoup.connect(e);
            if (params != null) {
                for (int doc = 0; doc < params.length; ++doc) {
                    conn.data(params[doc], values[doc]);
                }
            }

            Document var20 = null;
            switch (requestType) {
                case 0:
                    var20 = conn.timeout(100000).get();
                    break;
                case 1:
                    var20 = conn.timeout(100000).post();
            }

            Elements results = new Elements();
            Element result;
            switch (type) {
                case 0:
                    results = var20.getElementsByClass(resultTagName);
                    break;
                case 1:
                    result = var20.getElementById(resultTagName);
                    results.add(result);
                    break;
                case 2:
                    results = var20.select(resultTagName);
                    break;
                default:
                    if (TextUtil.isEmpty(resultTagName)) {
                        results = var20.getElementsByTag("body");
                    }
            }

            Iterator var13 = results.iterator();

            while (var13.hasNext()) {
                result = (Element) var13.next();
                Elements links = result.getElementsByTag("a");
                Iterator var16 = links.iterator();

                while (var16.hasNext()) {
                    Element link = (Element) var16.next();
                    String linkHref = link.attr("href");
                    String linkText = link.text();
                    data = new LinkTypeData();
                    data.setLinkHref(linkHref);
                    data.setLinkText(linkText);
                    datas.add(data);
                }
            }
        } catch (IOException var19) {
            var19.printStackTrace();
        }

        return datas;
    }

    private static void validateRule(Rule rule) {
        String url = rule.getUrl();
        if (TextUtil.isEmpty(url)) {
            throw new RuleException("url不能为空！");
        } else if (!url.startsWith("http://")) {
            throw new RuleException("url的格式不正确！");
        } else if (rule.getParams() != null && rule.getValues() != null && rule.getParams().length != rule.getValues().length) {
            throw new RuleException("参数的键值对个数不匹配！");
        }
    }
}
