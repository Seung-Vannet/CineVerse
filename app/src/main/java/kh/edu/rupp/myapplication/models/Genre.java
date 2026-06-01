package kh.edu.rupp.myapplication.models;

public class Genre {
    private String name;
    private int iconResId;

    public Genre(String name, int iconResId) {
        this.name = name;
        this.iconResId = iconResId;
    }

    public String getName() { return name; }
    public int getIconResId() { return iconResId; }
}
