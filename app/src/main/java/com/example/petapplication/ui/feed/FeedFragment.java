package com.example.petapplication.ui.feed;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petapplication.AddPostActivity;
import com.example.petapplication.MainActivity;
import com.example.petapplication.PostActivity;
import com.example.petapplication.R;
import com.example.petapplication.adapters.FeedAdapter;
import com.example.petapplication.model.Post;

import java.util.ArrayList;

public class FeedFragment extends Fragment {

    private FeedViewModel feedViewModel;

    private TextView addPostText;
    private ImageView addPostImage;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        feedViewModel =
                ViewModelProviders.of(this).get(FeedViewModel.class);

        View root = inflater.inflate(R.layout.fragment_feed, container, false);

        addPostText = root.findViewById( R.id.addPostText);
        addPostImage = root.findViewById( R.id.addPostImage);

        addPostText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddPostActivity();
            }
        });

        addPostImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddPostActivity();
            }
        });

        final RecyclerView recyclerView = root.findViewById(R.id.feedRecyclerView);
        recyclerView.setAdapter( new FeedAdapter( new ArrayList<Post>()));

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        feedViewModel.getPosts().observe(getViewLifecycleOwner(), new Observer<ArrayList<Post>>() {
            @Override
            public void onChanged(ArrayList<Post> topics) {
                recyclerView.setAdapter( new FeedAdapter( topics));
            }
        });
        return root;
    }

    private void openAddPostActivity() {
        Intent intent = new Intent( getContext(), AddPostActivity.class);
        startActivity( intent);
    }
}