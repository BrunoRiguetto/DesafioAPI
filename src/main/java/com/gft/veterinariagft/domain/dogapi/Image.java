package com.gft.veterinariagft.domain.dogapi;

import java.io.Serializable;

public class Image implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String id;
    public int width;
    public int height;
    public String url;
    
    public Image() {
    }

    public Image(String id, int width, int height, String url) {
        this.id = id;
        this.width = width;
        this.height = height;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    
}
