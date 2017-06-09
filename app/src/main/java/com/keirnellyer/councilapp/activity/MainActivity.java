package com.keirnellyer.councilapp.activity;

import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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

import com.keirnellyer.councilapp.HomeFragment;
import com.keirnellyer.councilapp.Location;
import com.keirnellyer.councilapp.LocationListFragment;
import com.keirnellyer.councilapp.R;

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

        // only initialize initial screen on first launch
        // this prevents losing fragment states' during orientation changes
        if (savedInstanceState == null) {
            selectItem(0, false);
        }

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
            selectItem(position, true);
        }
    }

    private void selectItem(int position, boolean animate) {
        if (position == 0) {
            Fragment fragment = new HomeFragment();
            changeContent(fragment, animate);
        } else {
            ArrayList<Location> locations = new ArrayList<>();
            String exampleDescription = getResources().getString(R.string.lorem_ipsum_dolor);

            switch (position) {
                case 1:
                    locations.add(new Location("Andrew Carnegie Birthplace", exampleDescription,
                            getString(R.string.url_andrew_carnegie_birthplace_image)));

                    locations.add(new Location("The Glen", exampleDescription,
                            getString(R.string.url_pittencrieff_park_image)));
                    break;
                case 2:
                    locations.add(new Location("Music Festival", exampleDescription,
                            getString(R.string.url_music_festival_image)));

                    locations.add(new Location("Farmers Market", exampleDescription,
                            getString(R.string.url_farmers_market_image)));

                    locations.add(new Location("Bike Race", "At Knockhill",
                            getString(R.string.url_knockhill_bike_race_image)));
                    break;
                case 3:
                    locations.add(new Location("Bella Italia", exampleDescription,
                            getString(R.string.url_bella_italia_image)));

                    locations.add(new Location("Luigi's", exampleDescription,
                            getString(R.string.url_luigis_image)));

                    locations.add(new Location("The Wood Mill", exampleDescription,
                            getString(R.string.url_the_wood_mill_image)));

                    locations.add(new Location("Toni Macaroni", exampleDescription,
                            getString(R.string.url_toni_macaroni_image)));
                    break;
                case 4:
                    locations.add(new Location("The City Hotel", exampleDescription,
                            getString(R.string.url_the_city_hotel_image)));

                    locations.add(new Location("Holiday Express", exampleDescription,
                            getString(R.string.url_holiday_express_image)));

                    locations.add(new Location("Premier Inn", exampleDescription,
                            getString(R.string.url_premier_inn_image)));
                    break;
                default:
                    return;
            }

            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("locations", locations);

            Fragment fragment = new LocationListFragment();
            fragment.setArguments(bundle);

            changeContent(fragment, animate);
        }

        // update selected item and title, then close the drawer
        drawerList.setItemChecked(position, true);
        setTitle(drawerTitles[position]);
        drawerLayout.closeDrawer(drawerList);
    }

    private void changeContent(Fragment fragment, boolean animate) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        if (animate) {
            fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        }

        fragmentTransaction.replace(R.id.content_frame, fragment);
        fragmentTransaction.commit();
    }
}
