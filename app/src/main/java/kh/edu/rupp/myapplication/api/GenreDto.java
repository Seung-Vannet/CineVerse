package kh.edu.rupp.myapplication.api;

import com.google.gson.annotations.SerializedName;

public class GenreDto {
    @SerializedName("name")
    private String name;

    public String getName() {
        return name;
    }
}
