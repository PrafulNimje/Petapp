package com.example.petapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.petapplication.model.Post;
import com.example.petapplication.model.User;
import com.example.petapplication.service.UserService;
import com.example.petapplication.serviceImpl.UserServiceImpl;
import com.example.petapplication.ui.feed.FeedFragment;
import com.example.petapplication.util.Callback;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddPostActivity extends AppCompatActivity {

    private ImageView selectedImageView;
    private Uri currentImageURI;

    private TextInputEditText editTitle, editCaption;
    
    private StorageReference storageReference;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;

    private UserService userService = new UserServiceImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        selectedImageView = findViewById( R.id.selectedImageView);
        editTitle = findViewById( R.id.editTitle);
        editCaption = findViewById( R.id.editCaption);

        storageReference = FirebaseStorage.getInstance().getReference().child("posts");
        databaseReference = FirebaseDatabase.getInstance().getReference().child("posts");
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void addPost(View view) {

        final String key = databaseReference.push().getKey();
        final StorageReference imageReference = storageReference.child( key);
        UploadTask task = storageReference.child( key).putFile( currentImageURI);

        task.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                return imageReference.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if( task.isSuccessful()) {
                    Uri downloadUri = task.getResult();
                    final String url = downloadUri.toString();

                    userService.getUserById(new Callback<User>() {
                        @Override
                        public User callback(User returningResult) {
                            User user = returningResult;

                            Post post = new Post( key,
                                    editTitle.getText().toString().trim(),
                                    editCaption.getText().toString().trim(),
                                    url,
                                    user);

                            databaseReference.child( key).setValue( post)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if( task.isSuccessful()) {
                                                Toast.makeText(AddPostActivity.this, "Posted Successfully", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent( getApplicationContext(), HomeActivity.class);
                                                startActivity( intent);
                                            }
                                        }
                                    });
                            return null;
                        }
                    }, firebaseAuth.getUid());

                }
            }
        });

    }

    public void selectImage( View view) {
        ImagePicker.Companion.with(this)
                //.cropSquare()	    			//Crop image(Optional), Check Customization for more option
                //.compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start();
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult( requestCode, resultCode, data);
        if (resultCode == -1) {
            currentImageURI = data != null ? data.getData() : null;
            selectedImageView.setImageURI( currentImageURI);
        } else if (resultCode == 64) {
            Toast.makeText(this, ImagePicker.Companion.getError(data), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show();
        }
    }
}