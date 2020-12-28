package com.dsi31g1.covid19;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.dsi31g1.covid19.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

    }
        private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedfragment  = null;

                switch (item.getItemId()){
                    case R.id.home:
                        selectedfragment = new home();
                        break;
                    case R.id.stat:
                        selectedfragment = new stat_fragment();
                        break;
                    case R.id.hospital:
                        selectedfragment=new List_hosp_fragment();
                        break;
                    case R.id.about:
                        selectedfragment=new about_fragment();
                        break;

                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment,selectedfragment).commit();
                return true;
            }

        };
        }