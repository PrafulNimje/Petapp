package com.example.petapplication.serviceImpl;

import androidx.annotation.NonNull;

import com.example.petapplication.model.Pet;
import com.example.petapplication.model.User;
import com.example.petapplication.service.UserService;
import com.example.petapplication.util.Callback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserServiceImpl implements UserService {

    private DatabaseReference databaseReference;

    public UserServiceImpl() {
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public void getUserById(final Callback<User> callback, String id) {
        databaseReference.child("users").child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue( User.class);
                callback.callback( user);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void savePetByUserId(final Callback<Boolean> callback, String id, Pet pet) {
        databaseReference.child("users").child(id).child("pet").setValue(pet)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if( task.isSuccessful()) {
                            callback.callback( true);
                        } else {
                            System.out.println("==============>"+task.getException().getStackTrace());
                            callback.callback( false);
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        System.out.println("==============>"+e.getStackTrace());
                        callback.callback( false);
                    }
                });
    }
}
