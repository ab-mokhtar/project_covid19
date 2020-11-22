package com.dsi31g1.covid19;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class About extends AppCompatActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_main);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.about);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int itemId = menuItem.getItemId();

                if (itemId == R.id.stat) {
                    About about = About.this;
                    about.startActivity(new Intent(about.getApplicationContext(), Stat.class));
                    About.this.overridePendingTransition(0, 0);
                    return true;
                } else if (itemId != R.id.stat) {
                    return itemId == R.id.home;
                } else {
                    About about2 = About.this;
                    about2.startActivity(new Intent(about2.getApplicationContext(), MainActivity.class));
                    About.this.overridePendingTransition(0, 0);
                    return true;
                }
            }
        });
    }
}
