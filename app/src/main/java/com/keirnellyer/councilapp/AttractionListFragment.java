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

public class AttractionListFragment extends Fragment {

    private final List<Attraction> attractions = new ArrayList<>();

    public AttractionListFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String exampleDescription = getResources().getString(R.string.lorem_ipsum_dolor);

        Attraction acBirthplace = new Attraction("Andrew Carnegie Birthplace", exampleDescription,
                R.drawable.andrew_carnegie_birthplace);
        attractions.add(acBirthplace);

        Attraction glen = new Attraction("The Glen", exampleDescription,
                R.drawable.dunfermline_glen);
        attractions.add(glen);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_attraction_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(new AttractionsViewAdapter(attractions));
        }

        return view;
    }
}
