package com.dsi31g1.covid19;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link stat_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class stat_fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public stat_fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment stat_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static stat_fragment newInstance(String param1, String param2) {
        stat_fragment fragment = new stat_fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
//récupération les chiffres statistique à partir d'un api (fichier json)
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.stat, container, false);
        final TextView t1= v.findViewById(R.id.nbm_tun);
        final TextView t2= v.findViewById(R.id.nbdcd_tun);
        final TextView t3= v.findViewById(R.id.etatg_tun);
        final TextView t4= v.findViewById(R.id.gueri_tun);
        final TextView t5= v.findViewById(R.id.nbcas_j);
        final TextView t6= v.findViewById(R.id.nbtest);
        final TextView t7= v.findViewById(R.id.nmb_tot);
        final TextView t8= v.findViewById(R.id.nbdcd_tot);
        final TextView t9= v.findViewById(R.id.etatg_monde);
        final TextView t10= v.findViewById(R.id.gueri_monde);

        RequestQueue mQueue = Volley.newRequestQueue(v.getContext());
            String url = "https://disease.sh/v3/covid-19/countries/Tunisia?strict=true";
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {

                                {
                                    String cases = response.getString("cases");
                                    String todayCases = response.getString("todayCases");
                                    String deaths = response.getString("deaths");
                                    String todayDeaths = response.getString("todayDeaths");
                                    String critical = response.getString("critical");
                                    String recovered = response.getString("recovered");
                                    String tests = response.getString("tests");
                                    t1.setText(cases);
                                    t2.setText(deaths);
                                    t3.setText(critical);
                                    t4.setText(recovered);
                                    t5.setText(todayCases);
                                    t6.setText(tests);



                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            });
            mQueue.add(request);
            /*récupération par l'api mondiale*/
         url = "https://disease.sh/v3/covid-19/all";
         request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            {
                                String cases = response.getString("cases");
                                String todayCases = response.getString("todayCases");
                                String deaths = response.getString("deaths");
                                String todayDeaths = response.getString("todayDeaths");
                                String critical = response.getString("critical");
                                String recovered = response.getString("recovered");
                                String tests = response.getString("tests");
                                t7.setText(cases);
                                t8.setText(deaths);
                                t9.setText(critical);
                                t10.setText(recovered);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
        return v;
    }
}