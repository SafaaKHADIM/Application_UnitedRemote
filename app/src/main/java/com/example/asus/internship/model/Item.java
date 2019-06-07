package com.example.asus.internship.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("stargazers_count")
    @Expose
    private int stargazersCount;
    @SerializedName("owner")
    @Expose
    private Owner owner;

    public Item(String name, String description, int stargazersCount, Owner owner){
        this.name = name;
        this.description = description;
        this.stargazersCount = stargazersCount;
        this.owner=owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStargazersCount() {
        return stargazersCount;
    }

    public void setStargazersCount(int stargazersCount) {
        this.stargazersCount = stargazersCount;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}
