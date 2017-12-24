package com.plugins.collector.parse.rules;

import com.plugins.collector.bean.CollectorType;

public class BaiduRule extends CommonRule {
    private CollectorType collectorType = CollectorType.Baidu;

    @Override
    public CollectorType getCollectorType() {
        return collectorType;
    }

    @Override
    public void setCollectorType(CollectorType collectorType) {
        this.collectorType = collectorType;
    }
}
