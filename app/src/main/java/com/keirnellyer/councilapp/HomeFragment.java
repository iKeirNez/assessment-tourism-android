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
        ImageView featureImage = (ImageView) view.findViewById(R.id.feature_image);

        Picasso.with(getContext())
                .load(getString(R.string.url_home_feature_image))
                .fit()
                .centerCrop()
                .into(featureImage, new Callback() {
                    @Override
                    public void onSuccess() {
                        view.findViewById(R.id.feature_image_loading_indicator).setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {

                    }
                });

        ImageView councilLogo = (ImageView) view.findViewById(R.id.council_logo);

        Picasso.with(getContext())
                .load(getString(R.string.url_council_logo_image))
                .fit()
                .centerInside()
                .into(councilLogo, new Callback() {
                    @Override
                    public void onSuccess() {
                        view.findViewById(R.id.council_logo_loading_indicator).setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {

                    }
                });

        return view;
    }
}
