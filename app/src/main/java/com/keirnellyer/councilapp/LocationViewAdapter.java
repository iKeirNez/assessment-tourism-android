package com.keirnellyer.councilapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LocationViewAdapter extends RecyclerView.Adapter<LocationViewAdapter.ViewHolder> {

    private final List<Location> mValues;
    //private final OnListFragmentInteractionListener mListener;

    public LocationViewAdapter(List<Location> items) {
        setHasStableIds(true);
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_location, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Location location = mValues.get(position);
        holder.mItem = location;
        holder.mTitleView.setText(location.getName());
        holder.mDescriptionView.setText(location.getDescription());

        Picasso.with(holder.mView.getContext())
                .load(location.getUrl())
                .fit()
                .centerInside()
                .into(holder.mImageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        holder.mLoadingIndicator.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {

                    }
                });

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (null != mListener) {
//                    // Notify the active callbacks interface (the activity, if the
//                    // fragment is attached to one) that an item has been selected.
//                    // TODO mListener.onListFragmentInteraction(holder.mItem);
//                }
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mTitleView;
        public final TextView mDescriptionView;
        public final ImageView mImageView;
        public final ProgressBar mLoadingIndicator;
        public Location mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTitleView = (TextView) view.findViewById(R.id.attraction_title);
            mDescriptionView = (TextView) view.findViewById(R.id.attraction_description);
            mImageView = (ImageView) view.findViewById(R.id.attraction_image);
            mLoadingIndicator = (ProgressBar) view.findViewById(R.id.feature_image_loading_indicator);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mDescriptionView.getText() + "'";
        }
    }
}
