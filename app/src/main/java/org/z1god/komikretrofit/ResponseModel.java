package org.z1god.komikretrofit;

import com.google.gson.annotations.SerializedName;

public class ResponseModel {
    @SerializedName("title")
    public String judul;

    @SerializedName("img")
    public String imgUrl;

    public ResponseModel(String judul, String imgUrl) {
        this.judul = judul;
        this.imgUrl = imgUrl;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
