package kh.edu.rupp.myapplication.models;

public class CastMember {
    private final String name;
    private final String character;
    private final String profileUrl;

    public CastMember(String name, String character, String profileUrl) {
        this.name = name;
        this.character = character;
        this.profileUrl = profileUrl;
    }

    public String getName() { return name; }
    public String getCharacter() { return character; }
    public String getProfileUrl() { return profileUrl; }
}
