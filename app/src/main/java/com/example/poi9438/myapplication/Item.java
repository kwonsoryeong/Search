package com.example.poi9438.myapplication;

public class Item {
    String image;
    String name;
    float score;
    int zzim;

    public String getImage() {
        return image;
    }
    public float getScore() {
        return score;
    }
    public String getName() {
        return name;
    }
    public int getZzim() {
        return zzim;
    }

    public Item(String image, String name, float score, int zzim) {
        this.image = image;
        this.name = name;
        this.score = score;
        this.zzim = zzim;
    }
}

