package com.example.petapplication.model;

public class Post {

    private String id;
    private String title;
    private String caption;
    private String imageUrl;
    private User user;

    public Post() { }

    public Post(String id, String title, String caption, String imageUrl, User user) {
        this.id = id;
        this.title = title;
        this.caption = caption;
        this.imageUrl = imageUrl;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
