package com.amazingname.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Site {
    private int id;
    private StringProperty city;

    public Site() {
        this.city = new SimpleStringProperty();
    }

    public Site(int id, String city) {
        this.id = id;
        this.city = new SimpleStringProperty(city);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCity() { return city.get(); }
    public void setCity(String city) { this.city.set(city); }
    public StringProperty cityProperty() { return city; }
}
