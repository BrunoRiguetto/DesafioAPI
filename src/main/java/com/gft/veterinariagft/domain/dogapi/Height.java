package com.gft.veterinariagft.domain.dogapi;

import java.io.Serializable;

public class Height implements Serializable{
    public String imperial;
    public String metric;

    public Height() {

    }

    public Height(String imperial, String metric) {
        this.imperial = imperial;
        this.metric = metric;
    }

    public String getImperial() {
        return imperial;
    }

    public void setImperial(String imperial) {
        this.imperial = imperial;
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }
}
