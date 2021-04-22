package com.example.petapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.petapplication.model.Post;
import com.example.petapplication.service.PostService;
import com.example.petapplication.serviceImpl.PostServiceImpl;
import com.example.petapplication.util.Callback;
import com.squareup.picasso.Picasso;

public class PostActivity extends AppCompatActivity {

    private TextView userNameView, titleView, captionView;
    private ImageView imageView, userPicView;

    private PostService postService = new PostServiceImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        userNameView = findViewById(R.id.postUserNameView);
        titleView = findViewById(R.id.postTitleView);
        captionView = findViewById(R.id.postCaptionView);
        imageView = findViewById(R.id.postImageView);


        String id = getIntent().getStringExtra("postId");

        postService.getPostById(new Callback<Post>() {
            @Override
            public Post callback(Post post) {
                userNameView.setText( post.getUser().getName());
                titleView.setText( post.getTitle());
                captionView.setText( post.getCaption());

                Picasso.get()
                        .load( post.getImageUrl())
                        .into( imageView);

                return null;
            }
        }, id);
    }
}