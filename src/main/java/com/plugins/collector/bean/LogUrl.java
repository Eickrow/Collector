package com.plugins.collector.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LogUrl")
public class LogUrl {
    @Id
    @GeneratedValue
    private long id;
    private ProcesserStatus processerStatus;
    private String url;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ProcesserStatus getProcesserStatus() {
        return processerStatus;
    }

    public void setProcesserStatus(ProcesserStatus processerStatus) {
        this.processerStatus = processerStatus;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
