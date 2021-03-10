package com.deepspc.workshop.model;

public class FaultItem {
    private String id;
    private String name;
    private String date;
    private Boolean checked;

    public FaultItem(String id, String name, String date) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.checked = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}
