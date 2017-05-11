package com.keirnellyer.councilapp;

import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private ActionBarDrawerToggle drawerToggle;

    private String[] drawerTitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        drawerTitles = getResources().getStringArray(R.array.navigation_items);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        drawerList = (ListView) findViewById(R.id.left_drawer);
        drawerList.setAdapter(new ArrayAdapter<>(this, R.layout.drawer_list_item, drawerTitles));
        drawerList.setOnItemClickListener(new DrawerItemClickListener());

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                toolbar.setTitle(getTitle());
                supportInvalidateOptionsMenu();
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                toolbar.setTitle(R.string.navigation_title);
                supportInvalidateOptionsMenu();
            }
        };

        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.setDrawerIndicatorEnabled(true);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        selectItem(0);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        // Handle your other action bar items...
        return super.onOptionsItemSelected(item);
    }

    /* The click listener for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {
        ArrayList<Location> locations = new ArrayList<>();
        String exampleDescription = getResources().getString(R.string.lorem_ipsum_dolor);

        switch (position) {
            case 0:
                locations.add(new Location("Andrew Carnegie Birthplace", exampleDescription,
                        R.drawable.attraction_andrew_carnegie_birthplace));

                locations.add(new Location("The Glen", exampleDescription,
                        R.drawable.attraction_dunfermline_glen));
                break;
            case 1:
                // TODO events
                break;
            case 2:
                locations.add(new Location("Bella Italia", exampleDescription,
                        R.drawable.restuarant_bella_italia));

                locations.add(new Location("Luigi's", exampleDescription,
                        R.drawable.restuarant_luigis));

                locations.add(new Location("The Wood Mill", exampleDescription,
                        R.drawable.restuarant_wood_mill));

                locations.add(new Location("Toni Macaroni", exampleDescription,
                        R.drawable.restuarant_toni_macaroni));
                break;
            case 3:
                locations.add(new Location("The City Hotel", exampleDescription,
                        R.drawable.hotel_city_hotel));

                locations.add(new Location("Holiday Express", exampleDescription,
                        R.drawable.hotel_holiday_express));

                locations.add(new Location("Premier Inn", exampleDescription,
                        R.drawable.hotel_premier_inn));
                break;
            default:
                return;
        }

        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("locations", locations);

        Fragment fragment = new LocationListFragment();
        fragment.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .replace(R.id.content_frame, fragment)
                .commit();

        // update selected item and title, then close the drawer
        drawerList.setItemChecked(position, true);
        setTitle(drawerTitles[position]);
        drawerLayout.closeDrawer(drawerList);
    }
}
