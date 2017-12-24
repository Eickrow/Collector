package com.plugins.collector.bean;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue
    private long id;
    private String target;
    @Enumerated(EnumType.STRING)
    private ProcesserStatus processerStatus;
    private Date createdTime;
    private Date updateTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public ProcesserStatus getProcesserStatus() {
        return processerStatus;
    }

    public void setProcesserStatus(ProcesserStatus processerStatus) {
        this.processerStatus = processerStatus;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
