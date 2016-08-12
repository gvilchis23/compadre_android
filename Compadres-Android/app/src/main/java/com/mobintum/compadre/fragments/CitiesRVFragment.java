package com.mobintum.compadre.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mobintum.compadre.R;
import com.mobintum.compadre.adapters.CityRVAdapter;
import com.mobintum.compadre.models.City;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CitiesRVFragment extends Fragment implements CityRVAdapter.OnItemClickListener {
    public static final String TAG = "CitiesRVFragment";
    private RecyclerView rvCities;
    private CityRVAdapter adapter;
    private Callbacks callback;
    private List<City> cities = City.getCities();

    public CitiesRVFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cities_rv, container, false);
        rvCities = (RecyclerView) view.findViewById(R.id.rvCities);
        adapter = new CityRVAdapter(cities,getContext(),this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvCities.setLayoutManager(linearLayoutManager);
        rvCities.setAdapter(adapter);


        return view;
    }

    @Override
    public void onClick(City city) {

    }

    public interface Callbacks{
        public void onCitySelected(City city);
    }
}
