package com.rodrigo.venuefinder.android.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.rodrigo.venuefinder.android.Global;
import com.rodrigo.venuefinder.android.R;
import com.rodrigo.venuefinder.android.fragments.VenueDetailFragment;
import com.rodrigo.venuefinder.android.model.Venue;

public class VenueDetailActivity extends ActionBarActivity {
    
	private Venue mItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        
        if (savedInstanceState == null) {
            Bundle arguments = new Bundle();
        	mItem = new Gson().fromJson(getIntent().getStringExtra(VenueDetailFragment.ARG_VENUE_OBJECT), Venue.class);
            arguments.putString(VenueDetailFragment.ARG_VENUE_OBJECT,
                    getIntent().getStringExtra(VenueDetailFragment.ARG_VENUE_OBJECT));
            VenueDetailFragment fragment = new VenueDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.venue_detail_container, fragment)
                    .commit();
        }
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_share_menu, menu);
        MenuItem item = menu.findItem(R.id.action_share);
        ShareActionProvider myShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
        myShareActionProvider.setShareIntent(Global.createShareIntent(mItem, getApplicationContext()));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            NavUtils.navigateUpTo(this, new Intent(this, VenueListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
