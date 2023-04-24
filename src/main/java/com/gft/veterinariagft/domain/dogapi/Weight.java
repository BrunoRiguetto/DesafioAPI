package com.gft.veterinariagft.domain.dogapi;

import java.io.Serializable;

public class Weight implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String imperial;
    public String metric;

    public Weight() {

    }

    public Weight(String imperial, String metric) {
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
