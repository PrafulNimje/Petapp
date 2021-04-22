package com.example.petapplication.service;

import com.example.petapplication.model.Pet;
import com.example.petapplication.model.User;
import com.example.petapplication.util.Callback;

public interface UserService {

    public void getUserById( Callback<User> user, String id);

    public void savePetByUserId(Callback<Boolean> isSuccessful, String id, Pet pet);

}
