package com.keirnellyer.councilapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class HomeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ImageView featureImage = (ImageView) view.findViewById(R.id.featureImage);

        Picasso.with(getContext())
                .load("http://www.bbc.co.uk/staticarchive/0bc20c8f207a3858706d5eb28eb269696b212257.jpg")
                .fit()
                .centerCrop()
                .into(featureImage);

        return view;
    }
}
