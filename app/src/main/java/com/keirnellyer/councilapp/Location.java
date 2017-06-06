package com.keirnellyer.councilapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Location implements Parcelable {
    private String name;
    private String description;
    private String url;

    public Location(String name, String description, String url) {
        this.name = name;
        this.description = description;
        this.url = url;
    }

    protected Location(Parcel in) {
        this.name = in.readString();
        this.description = in.readString();
        this.url = in.readString();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeString(this.url);
    }

    public static final Parcelable.Creator<Location> CREATOR = new Parcelable.Creator<Location>() {
        @Override
        public Location createFromParcel(Parcel source) {
            return new Location(source);
        }

        @Override
        public Location[] newArray(int size) {
            return new Location[size];
        }
    };
}
