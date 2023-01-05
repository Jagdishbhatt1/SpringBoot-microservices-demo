package io.javabrains.moviecatalogueservice.models;

public class Catalogitem {

    String name;
    String des;
    int rating;

    public Catalogitem(String name, String des, int rating) {
        this.name = name;
        this.des = des;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
