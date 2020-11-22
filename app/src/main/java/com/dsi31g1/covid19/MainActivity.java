package com.dsi31g1.covid19;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                if (itemId == R.id.about) {
                    MainActivity mainActivity = MainActivity.this;
                    mainActivity.startActivity(new Intent(mainActivity.getApplicationContext(), About.class));
                    MainActivity.this.overridePendingTransition(0, 0);
                    return true;
                } else if (itemId != R.id.stat) {
                    return itemId == R.id.home;
                } else {
                    MainActivity mainActivity2 = MainActivity.this;
                    mainActivity2.startActivity(new Intent(mainActivity2.getApplicationContext(), Stat.class));
                    MainActivity.this.overridePendingTransition(0, 0);
                    return true;
                }
            }
        });
    }
}
