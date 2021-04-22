package com.example.petapplication.serviceImpl;

import androidx.annotation.NonNull;

import com.example.petapplication.model.Pet;
import com.example.petapplication.model.Post;
import com.example.petapplication.service.PostService;
import com.example.petapplication.util.Callback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PostServiceImpl implements PostService {

    private DatabaseReference databaseReference;

    public PostServiceImpl() {
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public void getPostById(final Callback<Post> callback, String id) {
        databaseReference.child("posts").child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Post post = dataSnapshot.getValue( Post.class);
                callback.callback( post);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
