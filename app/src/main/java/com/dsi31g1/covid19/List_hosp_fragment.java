package com.dsi31g1.covid19;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

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
        View RootView = inflater.inflate(R.layout.fragment_list_hosp_fragment, container, false);
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
        final ListView list1 = RootView.findViewById(R.id.ListeView1);
        SimpleAdapter adapter = new SimpleAdapter(getActivity().getBaseContext(),list,R.layout.ligne, from, to);
        list1.setAdapter(adapter);
        list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(getContext(),list.get(position).get("title"),Toast.LENGTH_LONG).show();
            }

        });
        list1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Sélection Item");
                builder.setMessage("Hôspital: "+list.get(position).get("Nom")+" Adresse: "+" "+list.get(position).get("lieu")+" nombre de lite occupé: "+list.get(position).get("nblit")+" Tel:"+list.get(position).get("phone"));
                builder.setCancelable(true);
                builder.setPositiveButton("ok", null).show();

                return true;
            }
        });

        // Inflate the layout for this fragment
        return RootView;


    }
}