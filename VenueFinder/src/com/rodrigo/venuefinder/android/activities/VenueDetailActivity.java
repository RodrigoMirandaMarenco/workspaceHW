package com.rodrigo.venuefinder.android.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;

import com.rodrigo.venuefinder.android.Global;
import com.rodrigo.venuefinder.android.R;
import com.rodrigo.venuefinder.android.fragments.VenueDetailFragment;
import com.rodrigo.venuefinder.android.model.Venue;

public class VenueDetailActivity extends ActionBarActivity {
    
	private Venue mVenue;
	
    private Menu mOptionsMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        
        if (savedInstanceState == null) {
            mVenue = (Venue) getIntent().getParcelableExtra(Venue.class.getName());
            getSupportFragmentManager().beginTransaction()
            		.add(R.id.venue_detail_container, VenueDetailFragment.getInstance(mVenue))
                	.commit();
        }
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mOptionsMenu = menu;
        getMenuInflater().inflate(R.menu.action_bar_share_menu, mOptionsMenu);
        updateMenu();
        return true;
    }
    
    private void updateMenu() {
        if (mOptionsMenu != null && mVenue != null) {
            MenuItem item = mOptionsMenu.findItem(R.id.action_share);
            item.setVisible(true);
            ShareActionProvider myShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
            myShareActionProvider.setShareIntent(Global.createShareIntent(mVenue, getApplicationContext()));
        }
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
