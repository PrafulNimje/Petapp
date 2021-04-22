package com.example.petapplication.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.petapplication.R;
import com.example.petapplication.model.Pet;
import com.example.petapplication.model.User;
import com.example.petapplication.service.UserService;
import com.example.petapplication.serviceImpl.UserServiceImpl;
import com.example.petapplication.util.Callback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileFragment extends Fragment {

    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;

    private LinearLayout addPetLayout, createPetLayout;

    private ImageView addPetButton;
    private Button createPetBtn;
    private TextView profileName, profileMail, profilePhone;
    private TextInputEditText petNameEdit, petAgeEdit, petCategoryEdit, petGenderEdit, petHeightEdit, petWeightEdit;

    UserService userService = new UserServiceImpl();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, final Bundle savedInstanceState) {

        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();

        final View root = inflater.inflate(R.layout.fragment_profile, container, false);
        createPetLayout = root.findViewById( R.id.createPetLayout);

        setInitialViews( root);

        databaseReference.child("users").child(firebaseAuth.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                profileName.setText( user.getName());
                profileMail.setText( user.getMail());
                profilePhone.setText( user.getPhone());

                if( user.getPet()==null) {

                } else {


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        addPetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragment().getActivity().findViewById( R.id.addPetBtn).setVisibility( View.GONE);
                // addPetButton.setVisibility( View.GONE);
            }
        });

        createPetBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                addPetButton.setVisibility( View.GONE);

                Pet pet = new Pet();
                pet.setName( petNameEdit.getText().toString().trim());
                pet.setCategory( petCategoryEdit.getText().toString().trim());
                pet.setDob( petAgeEdit.getText().toString().trim());
                pet.setGender( petGenderEdit.getText().toString().trim());
                pet.setHeight( Double.parseDouble( petHeightEdit.getText().toString().trim()));
                pet.setWeight( Double.parseDouble( petWeightEdit.getText().toString().trim()));


                databaseReference.child("users").child( firebaseAuth.getUid()).child("pet").setValue(pet)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if( task.isSuccessful()) {

                                } else {

                                }
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                System.out.println("==============>"+e.getStackTrace());
                            }
                        });

//                userService.savePetByUserId(new Callback<Boolean>() {
//                    @Override
//                    public Boolean callback(Boolean isSuccessful) {
//
//                        if( isSuccessful) {
//                            createPetLayout.setVisibility( View.GONE);
//                            addPetLayout.setVisibility( View.GONE);
//                        }
//
//                        return null;
//                    }
//                }, firebaseAuth.getUid(), pet);
            }

        });


        return root;
    }

    private void setInitialViews( View root) {
        addPetButton = root.findViewById( R.id.addPetBtn);
        profileName = root.findViewById( R.id.profileName);
        profileMail = root.findViewById( R.id.profileMail);
        profilePhone = root.findViewById( R.id.profilePhone);

        petNameEdit = root.findViewById( R.id.editPetName);
        petAgeEdit = root.findViewById( R.id.editPetAge);
        petCategoryEdit = root.findViewById( R.id.editPetCategory);
        petGenderEdit = root.findViewById( R.id.editPetGender);
        petWeightEdit = root.findViewById( R.id.editPetWeight);
        petHeightEdit = root.findViewById( R.id.editPetHeight);

        createPetBtn = root.findViewById( R.id.createPetBtn);
    }
}