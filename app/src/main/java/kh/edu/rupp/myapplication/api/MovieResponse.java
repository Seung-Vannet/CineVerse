package kh.edu.rupp.myapplication.api;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class MovieResponse {
    @SerializedName("results")
    private List<MovieDto> results;

    public List<MovieDto> getResults() {
        return results;
    }
}
