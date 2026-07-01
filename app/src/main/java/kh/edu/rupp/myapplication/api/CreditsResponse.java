package kh.edu.rupp.myapplication.api;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class CreditsResponse {
    @SerializedName("cast")
    private List<CastDto> cast;

    public List<CastDto> getCast() {
        return cast;
    }
}
