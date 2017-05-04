package com.keirnellyer.councilapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AttractionsViewAdapter extends RecyclerView.Adapter<AttractionsViewAdapter.ViewHolder> {

    private final List<Attraction> mValues;
    //private final OnListFragmentInteractionListener mListener;

    public AttractionsViewAdapter(List<Attraction> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_attraction, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Attraction attraction = mValues.get(position);
        holder.mItem = attraction;
        holder.mTitleView.setText(attraction.getName());
        holder.mDescriptionView.setText(attraction.getDescription());
        holder.mImageView.setImageResource(attraction.getImageResId());

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
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mTitleView;
        public final TextView mDescriptionView;
        public final ImageView mImageView;
        public Attraction mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTitleView = (TextView) view.findViewById(R.id.attraction_title);
            mDescriptionView = (TextView) view.findViewById(R.id.attraction_description);
            mImageView = (ImageView) view.findViewById(R.id.attraction_image);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mDescriptionView.getText() + "'";
        }
    }
}
