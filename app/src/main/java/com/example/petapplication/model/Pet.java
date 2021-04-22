package com.example.petapplication.model;

public class Pet {
    private String name;
    private String category;
    private String image;
    private String gender;
    private String dob;
    private Double weight;
    private Double height;

    public Pet() { }

    public Pet(String name, String category, String image, String gender, String dob, Double weight, Double height) {
        this.name = name;
        this.category = category;
        this.image = image;
        this.gender = gender;
        this.dob = dob;
        this.weight = weight;
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }
}
