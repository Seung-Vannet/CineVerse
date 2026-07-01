package kh.edu.rupp.myapplication.api;

import com.google.gson.annotations.SerializedName;

public class VideoDto {
    @SerializedName("key")
    private String key;

    @SerializedName("site")
    private String site;

    @SerializedName("type")
    private String type;

    public String getKey() { return key; }
    public String getSite() { return site; }
    public String getType() { return type; }
}
