package com.plugins.collector.processers;

import com.google.gson.Gson;
import com.plugins.collector.bean.CollectorType;
import com.plugins.collector.bean.PageInfo;
import com.plugins.collector.bean.ProcesserStatus;
import com.plugins.collector.config.HttpClientConfiguration;
import com.plugins.collector.parse.rules.CommonRule;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

public abstract class CommonProcesser {
    public static Gson gson = new Gson();

    public PageInfo execute(String url, CommonRule rule) throws IOException {
        HttpClientConfiguration httpClientConfiguration = new HttpClientConfiguration();
        httpClientConfiguration.setHeaders(rule.getHeaders());
        HttpClient httpClient = httpClientConfiguration.init().build();
        HttpGet httpget = new HttpGet(url);
        HttpResponse response = httpClient.execute(httpget);
        PageInfo pageInfo = convertRepsonse(response);
        pageInfo.setCollectorType(rule.getCollectorType());
        pageInfo.setProcesserStatus(ProcesserStatus.completed);
        pageInfo.setUrl(url);
        return pageInfo;
    }

    public List<String> extractUrl(PageInfo pageInfo) throws MalformedURLException, URISyntaxException {
        List<String> urls = new ArrayList<>();
        Document doc = Jsoup.parse(pageInfo.getBody());
        Elements links = doc.select("a[href]");
        if (links.size() != 0) {
            URI uri = new URL(pageInfo.getUrl()).toURI();
            for (Element link : links) {
                String linkHref = getUrl(uri, link.attr("href"));
                urls.add(linkHref);
            }
        }
        return urls;
    }

    private String getUrl(URI uri, String url) {
        if (url.startsWith("\\/")) {
            url = uri.getScheme() + ":" + uri.getSchemeSpecificPart() + url;
        }
        if (url.startsWith("\\/\\/")) {
            url = uri.getScheme() + ":" + url;
        }
        return url;
    }

    private PageInfo convertRepsonse(HttpResponse response) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setCreatedTime(new Date());
        pageInfo.setLocale(gson.toJson(response.getLocale()));
        pageInfo.setProtocolVersion(gson.toJson(response.getProtocolVersion()));
        pageInfo.setHeaders(convertHeaders(response.getAllHeaders()));
        try {
            pageInfo.setBody(EntityUtils.toString(response.getEntity()));
        } catch (IOException e) {
            pageInfo.setBody(e.getMessage());
        }
        return pageInfo;
    }

    private String convertHeaders(Header[] headers) {
        Map<String, Object> headerMap = new HashMap<>();
        for (Header header : headers) {
            headerMap.put(header.getName(), header.getValue());
        }
        return gson.toJson(headerMap);
    }
}
