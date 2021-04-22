package com.example.petapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private BottomNavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        NavController navController = getNavigationController();

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                if(destination.getId() == R.id.navigation_feed) {
                    System.out.println("======> Feed");
                }
                if(destination.getId() == R.id.navigation_medical) {
                    System.out.println("======> Medical");
                }
                if(destination.getId() == R.id.navigation_emergency) {
                    System.out.println("======> Emergency");
                }
                if(destination.getId() == R.id.navigation_food) {
                    System.out.println("======> Food");
                }
                if(destination.getId() == R.id.navigation_profile) {
                    System.out.println("======> Profile");
                }
            }
        });
    }

    private NavController getNavigationController( ) {
        navView = findViewById(R.id.nav_view);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_feed, R.id.navigation_medical, R.id.navigation_emergency, R.id.navigation_food, R.id.navigation_profile)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);

        return navController;
    }

}