package com.fauzi.sidigiapps.Model;

import java.util.List;

public class ResponseModel {
    private int kode;
    private String pesan;
    private List<DataLayanan>data;

    public int getKode() {
        return kode;
    }

    public void setKode(int kode) {
        this.kode = kode;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public List<DataLayanan> getData() {
        return data;
    }

    public void setData(List<DataLayanan> data) {
        this.data = data;
    }
}
