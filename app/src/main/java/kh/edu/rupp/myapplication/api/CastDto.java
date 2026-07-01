package kh.edu.rupp.myapplication.api;

import com.google.gson.annotations.SerializedName;

public class CastDto {
    @SerializedName("name")
    private String name;

    @SerializedName("character")
    private String character;

    @SerializedName("profile_path")
    private String profilePath;

    public String getName() { return name; }
    public String getCharacter() { return character; }
    public String getProfilePath() { return profilePath; }
}
