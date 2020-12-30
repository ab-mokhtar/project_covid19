package com.dsi31g1.covid19;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.dsi31g1.covid19.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class ActivityFragment extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new home()).commit();

        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }


    @Override
    public void onBackPressed() {
        Toast.makeText(this,"Veuillez déconnecté",Toast.LENGTH_LONG).show();
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
                    case R.id.samu:
                        selectedfragment= new samu_fragmen();
                        break;

                }
                assert selectedfragment != null;
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment,selectedfragment).commit();
                return true;
            }

        };

    public void logout(MenuItem item)
    {
        mAuth.signOut();
        Intent intent = new Intent(ActivityFragment.this, MainActivity.class);
        startActivity(intent);
    }
}