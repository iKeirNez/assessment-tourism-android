package com.keirnellyer.councilapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class LocationListFragment extends Fragment {

    private final List<Location> locations = new ArrayList<>();

    public LocationListFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String exampleDescription = getResources().getString(R.string.lorem_ipsum_dolor);

        Location acBirthplace = new Location("Andrew Carnegie Birthplace", exampleDescription,
                R.drawable.andrew_carnegie_birthplace);
        this.locations.add(acBirthplace);

        Location glen = new Location("The Glen", exampleDescription,
                R.drawable.dunfermline_glen);
        this.locations.add(glen);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_location_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(new LocationViewAdapter(locations));
        }

        return view;
    }
}
