package com.plugins.collector.parse.rules;

import com.plugins.collector.bean.CollectorType;
import com.plugins.collector.bean.ProcesserStatus;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.message.BasicHeader;

import java.util.ArrayList;
import java.util.List;

public class CommonRule {
    private CollectorType collectorType;
    private List<Header> headers = initHeaders();
    private List<String> urlParseRules = initUrlParseRule();

    public CollectorType getCollectorType() {
        return collectorType;
    }

    public void setCollectorType(CollectorType collectorType) {
        this.collectorType = collectorType;
    }

    public List<Header> getHeaders() {
        return headers;
    }

    public void setHeaders(List<Header> headers) {
        this.headers = headers;
    }


    public List<String> getUrlParseRules() {
        return urlParseRules;
    }

    public void setUrlParseRules(List<String> urlParseRules) {
        this.urlParseRules = urlParseRules;
    }

    private List<Header> initHeaders() {
        this.headers = new ArrayList<>();
        this.headers.add(new BasicHeader(HttpHeaders.USER_AGENT, "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36"));
        return this.headers;
    }

    private List<String> initUrlParseRule() {
        this.urlParseRules = new ArrayList<>();
        this.urlParseRules.add("a[href]");
        return this.urlParseRules;
    }
}
