package com.example.petapplication.ui.feed;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.petapplication.model.Post;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FeedViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Post>> allPosts;

    private DatabaseReference databaseReference;

    public FeedViewModel() {
        allPosts = new MutableLiveData<>();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("posts");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Post> posts = new ArrayList<Post>();
                for ( DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    posts.add( dataSnapshot.getValue( Post.class));
                }
                allPosts.setValue( posts);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
    }

    public MutableLiveData<ArrayList<Post>> getPosts() {
        return allPosts;
    }
}