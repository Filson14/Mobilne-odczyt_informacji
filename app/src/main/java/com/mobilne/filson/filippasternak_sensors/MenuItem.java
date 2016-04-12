package com.mobilne.filson.filippasternak_sensors;


/**
 * Created by Filson on 2016-04-11.
 */
public class MenuItem {
    private String id = null;
    private int icon;
    private String title;
    private boolean isHeader = false;

    public MenuItem(String title) {
        this(null, -1, title);
        isHeader = true;
    }

    public MenuItem (String id, int icon, String title) {
        super();
        this.id = id;
        this.icon = icon;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public int getIcon() {
        return icon;
    }

    public boolean isHeader() {
        return isHeader;
    }

    public String getId() { return id; }
}
