package com.keirnellyer.councilapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class HomeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_home, container, false);
        ImageView featureImage = (ImageView) view.findViewById(R.id.featureImage);

        Picasso.with(getContext())
                .load(getString(R.string.url_home_feature_image))
                .fit()
                .centerCrop()
                .into(featureImage, new Callback() {
                    @Override
                    public void onSuccess() {
                        view.findViewById(R.id.loading_indicator).setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {

                    }
                });

        return view;
    }
}
