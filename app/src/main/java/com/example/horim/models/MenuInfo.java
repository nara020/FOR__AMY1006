package com.example.horim.models;

public class MenuInfo {
    public int drawableId;
    public String name;
    public String explain;
    public String price;
    public String allegi;
    public String halal;
    public String spicy;

    public MenuInfo(int drawableId, String name , String explain , String price , String allegi, String halal , String spicy){
        this.drawableId = drawableId;
        this.name = name;
        this.explain =  explain;
        this.price = price;
        this.allegi = allegi;
        this.halal = halal;
        this.spicy = spicy;
    }
}
