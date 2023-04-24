package com.gft.veterinariagft.DTOs.error;

public class ErroDetalhes {
    private String titulo;
    private int status;
    private String detalhes;
    private long timeStamp;
    
    public ErroDetalhes(long currentTimeMillis, int value, Throwable cause) {
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getDetalhes() {
        return detalhes;
    }
    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }
    public long getTimeStamp() {
        return timeStamp;
    }
    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
