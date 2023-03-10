package com.qmart.qmartapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.qmart.qmartapp.Fragments.BasketFragment;
import com.qmart.qmartapp.Fragments.FavouriteFragment;
import com.qmart.qmartapp.Fragments.HomeFragment;
import com.qmart.qmartapp.Fragments.ProfileFragment;

public class MenuActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    HomeFragment homeFragment = new HomeFragment();
    BasketFragment basketFragment = new BasketFragment();
    FavouriteFragment favouriteFragment = new FavouriteFragment();
    ProfileFragment profileFragment = new ProfileFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        bottomNavigationView  = findViewById(R.id.bottom_navigation);

        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();
                        return true;
                    case R.id.basket:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,basketFragment).commit();
                        return true;
                    case R.id.favourites:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,favouriteFragment).commit();
                        return true;
                    case R.id.profile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,profileFragment).commit();
                        return true;
                }

                return false;
            }
        });


    }


}