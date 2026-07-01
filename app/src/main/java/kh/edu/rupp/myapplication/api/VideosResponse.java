package kh.edu.rupp.myapplication.api;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class VideosResponse {
    @SerializedName("results")
    private List<VideoDto> results;

    public List<VideoDto> getResults() {
        return results;
    }
}
