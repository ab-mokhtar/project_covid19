package com.dsi31g1.covid19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Stat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.stat);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                if (itemId == R.id.about) {
                    Stat stat = Stat.this;
                    stat.startActivity(new Intent(stat.getApplicationContext(), About.class));
                    Stat.this.overridePendingTransition(0, 0);
                    return true;
                } else if (itemId != R.id.stat) {
                    return itemId == R.id.home;
                } else {
                    Stat stat2 = Stat.this;
                    stat2.startActivity(new Intent(stat2.getApplicationContext(), MainActivity.class));
                    Stat.this.overridePendingTransition(0, 0);
                    return true;
                }
            }
        });
    }}

