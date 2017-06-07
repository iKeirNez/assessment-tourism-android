package com.keirnellyer.councilapp.activity;

import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
        selectItem(0, false);
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
                            "https://media-cdn.tripadvisor.com/media/photo-s/0b/0f/5c/c2/andrew-carnegie-birthplace.jpg"));

                    locations.add(new Location("The Glen", exampleDescription,
                            "http://www.balmule.co.uk/wp-content/uploads/2015/08/pittencrieff-park-entrance-dunfermline.jpg"));
                    break;
                case 2:
                    locations.add(new Location("Music Festival", exampleDescription,
                            "http://cdn.pitchfork.com/features/8891/92bc315a.jpg"));

                    locations.add(new Location("Farmers Market", exampleDescription,
                            "http://www.fifefarmersmarket.co.uk/assets/images/markets-dfme/TDR_7214DUN.jpg"));

                    locations.add(new Location("Bike Race", "At Knockhill",
                            "http://www.knockhill.com/wordpress/wp-content/uploads/2013/04/Teasdale-Dominant-at-Knockhill.jpg"));
                    break;
                case 3:
                    locations.add(new Location("Bella Italia", exampleDescription,
                            "https://media-cdn.tripadvisor.com/media/photo-s/06/42/c8/e4/bella-italia-dunfermline.jpg"));

                    locations.add(new Location("Luigi's", exampleDescription,
                            "https://media-cdn.tripadvisor.com/media/photo-s/08/2b/61/f3/luigi-s-restaurant.jpg"));

                    locations.add(new Location("The Wood Mill", exampleDescription,
                            "https://www.gourmetsociety.co.uk/uploads/images/restaurants/90f87d0fc70db2f479b81cbe5e6f77a7-image.png"));

                    locations.add(new Location("Toni Macaroni", exampleDescription,
                            "https://media-cdn.tripadvisor.com/media/photo-s/0f/51/e4/c6/photo-two.jpg"));
                    break;
                case 4:
                    locations.add(new Location("The City Hotel", exampleDescription,
                            "https://media-cdn.tripadvisor.com/media/photo-s/03/15/ba/62/the-city-hotel.jpg"));

                    locations.add(new Location("Holiday Express", exampleDescription,
                            "http://ihg.scene7.com/is/image/ihg/holiday-inn-express-dunfermline-2531769741-4x3"));

                    locations.add(new Location("Premier Inn", exampleDescription,
                            "http://www.premierinn.com/content/dam/pi/websites/hotelimages/gb/en/D/DUNDIS/xDUNDIS,P201.jpg.pagespeed.ic.2anCUnWFEf.jpg"));
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
