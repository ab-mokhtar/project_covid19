package com.dsi31g1.covid19;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link List_hosp_fragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class List_hosp_fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment List_hosp_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static List_hosp_fragment newInstance(String param1, String param2) {
        List_hosp_fragment fragment = new List_hosp_fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public List_hosp_fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /*lecture des données depuis la base de données (firebase) et les mettre dans une liste view */
        final View RootView = inflater.inflate(R.layout.fragment_list_hosp_fragment, container, false);
        final ArrayList<HashMap<String,String>> list= new ArrayList();
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://auth-4a095-default-rtdb.firebaseio.com/");
        DatabaseReference myRef = database.getReference("hospitales");
        final TextView test= RootView.findViewById(R.id.test);
        String[]from={"Nom","lieu","nblit"};
        int[] to= { R.id.titreview, R.id.descr,R.id.nb_lit};
        //LogAdapter logAdapter = new LogAdapter (this, Log, LogImg);
        final ListView list1 = RootView.findViewById(R.id.ListeView1);
        final SimpleAdapter adapter = new SimpleAdapter(getActivity().getBaseContext(),list,R.layout.ligne, from, to);
        list1.setAdapter(adapter);
        list1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Sélection Item");
                builder.setMessage("Hôspital: "+list.get(position).get("Nom")+" Adresse: "+" "+list.get(position).get("lieu")+" nombre de lit occupé: "+list.get(position).get("nblit")+" Tel:"+list.get(position).get("phone"));
                builder.setCancelable(true);
                builder.setPositiveButton("ok", null).show();

                return true;
            }
        });

        myRef.addChildEventListener(new ChildEventListener() {
                                        @Override
                                        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                                            HashMap<String,String>Log= new HashMap<String,String>();
                                            String nom = snapshot.child("nom").getValue().toString();
                                            String lieu = snapshot.child("lieu").getValue().toString();
                                            String nb_lit = snapshot.child("nb_lit").getValue().toString();
                                            String tel = snapshot.child("tel").getValue().toString();
                                            Log.put("Nom", nom);
                                            Log.put("lieu", lieu);
                                            Log.put("phone", tel);
                                            Log.put("nblit", nb_lit);
                                            list.add(Log);

                                            adapter.notifyDataSetChanged();
                                        }

                                        @Override
                                        public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                                        }

                                        @Override
                                        public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                                        }

                                        @Override
                                        public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });


                // Inflate the layout for this fragment
        return RootView;


    }
}