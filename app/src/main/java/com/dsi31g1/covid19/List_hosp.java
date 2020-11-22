package com.dsi31g1.covid19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.HashMap;

public class List_hosp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_hosp);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.hospital);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                if (itemId == R.id.hospital) {
                    List_hosp list = List_hosp.this;
                    list.startActivity(new Intent(list.getApplicationContext(), About.class));
                    List_hosp.this.overridePendingTransition(0, 0);
                    return true;
                } else if (itemId != R.id.hospital) {
                    return itemId == R.id.home;
                } else {
                    List_hosp list2 = List_hosp.this;
                    list2.startActivity(new Intent(list2.getApplicationContext(), List_hosp.class));
                    List_hosp.this.overridePendingTransition(0, 0);
                    return true;
                }
            }
        });

        final ArrayList<HashMap<String,String>> list= new ArrayList();
        HashMap<String,String>Log= new HashMap<String,String>();
        Log.put("Nom","hbib thamer");
        Log.put("lieu"," rue hbib thamer tunis");
        Log.put("nblit","50/50");
        Log.put("phone","71380216");
        list.add(Log);

        String[]from={"Nom","lieu","nblit"};
        int[] to= { R.id.titreview, R.id.descr,R.id.nb_lit};
        //LogAdapter logAdapter = new LogAdapter (this, Log, LogImg);
        final ListView list1 = findViewById(R.id.ListeView1);
        SimpleAdapter adapter = new SimpleAdapter(this,list,R.layout.ligne, from, to);
        list1.setAdapter(adapter);
        list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),list.get(position).get("title"),Toast.LENGTH_LONG).show();
            }

        });
        list1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(List_hosp.this);
                builder.setTitle("Sélection Item");
                builder.setMessage("Hôspital: "+list.get(position).get("Nom")+" Adresse: "+" "+list.get(position).get("lieu")+" nombre de lite occupé: "+list.get(position).get("nblit")+" Tel:"+list.get(position).get("phone"));
                builder.setCancelable(true);
                builder.setPositiveButton("ok", null).show();

                return true;
            }
        });


    }}