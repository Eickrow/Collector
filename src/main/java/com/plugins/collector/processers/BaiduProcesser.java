package com.plugins.collector.processers;

import com.plugins.collector.bean.LogUrl;
import com.plugins.collector.bean.PageInfo;
import com.plugins.collector.parse.rules.BaiduRule;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @author david
 */
@Service
public class BaiduProcesser extends CommonProcesser {
    public BaiduRule baiduRule = new BaiduRule();

    public void initAndStart(String url) throws IOException, URISyntaxException {
        PageInfo pageInfo = execute(url, baiduRule);
        extractUrl(pageInfo).parallelStream().map(urlStr->{
            LogUrl logUrl=new LogUrl();
            logUrl.setUrl(urlStr);
            return logUrl;
        }).forEach(logurl->{
//            System.out.println(gson.toJson(logUrlDao.save(logurl)));
        });
//        System.out.println(gson.toJson(logUrlDao.findAll()));
    }

}
