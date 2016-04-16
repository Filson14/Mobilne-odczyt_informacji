package com.mobilne.filson.filippasternak_sensors.device;

/**
 * Created by Filson on 2016-04-16.
 */
public class DeviceInfoItem {
    private String label;
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public DeviceInfoItem() {
        this("unknown", "");
    }

    public DeviceInfoItem(String label) {
        this(label, "");
    }

    public DeviceInfoItem(String label, String value) {
        this.label = label;
        this.value = value;
    }
}
