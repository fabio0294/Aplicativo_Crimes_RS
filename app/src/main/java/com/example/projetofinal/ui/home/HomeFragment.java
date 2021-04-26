package com.example.projetofinal.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.projetofinal.R;
import com.example.projetofinal.ui.adapter.CrimeAdapter;
import com.example.projetofinal.ui.model.TipoCrime;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements Response.Listener<JSONArray>,
        Response.ErrorListener{
    private HomeViewModel homeViewModel;
    private String url;
    List<TipoCrime> tiposCrimes =  new ArrayList<>();
    private RecyclerView rv;
    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        root = inflater.inflate(R.layout.fragment_home, container, false);
        /*final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        url = "https://babyier.rocks/uniritter/DadosCrimeRS2020_NomeTiposCrimes.json";
        RequestQueue queue = Volley.newRequestQueue(getContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                this, this);
        queue.add(jsonArrayRequest);

        return root;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
    }

    @Override
    public void onResponse(JSONArray response) {
        try {
            Log.e("OPA","1");
            for(int i = 0; i < response.length(); i++) {
                JSONObject json = null;
                json = response.getJSONObject(i);

                Log.e("OPA",json.getString("TIPO"));

                TipoCrime obj = new TipoCrime(json.getString("TIPO"),900);
                obj.getCrimeTipo();
                tiposCrimes.add(obj);
            }
            Log.e("OPA","2");
            rv = root.findViewById(R.id.RecyclerViewListApi);
            rv.setLayoutManager(new LinearLayoutManager(getContext()));
            Log.e("OPA","3");

            CrimeAdapter crimeAdapter = new CrimeAdapter(tiposCrimes, R.layout.content_crimes);
            Log.e("OPA","4");
            rv.setAdapter(crimeAdapter);

            Log.e("OPA","5");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}