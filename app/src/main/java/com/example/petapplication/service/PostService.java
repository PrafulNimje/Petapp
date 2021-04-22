package com.example.petapplication.service;

import com.example.petapplication.model.Pet;
import com.example.petapplication.model.Post;
import com.example.petapplication.model.User;
import com.example.petapplication.util.Callback;

public interface PostService {

    public void getPostById(Callback<Post> post, String id);

}
