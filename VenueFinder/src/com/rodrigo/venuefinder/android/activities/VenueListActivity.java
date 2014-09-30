package com.rodrigo.venuefinder.android.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;

import com.rodrigo.venuefinder.android.Global;
import com.rodrigo.venuefinder.android.R;
import com.rodrigo.venuefinder.android.fragments.VenueDetailFragment;
import com.rodrigo.venuefinder.android.fragments.VenueListFragment;
import com.rodrigo.venuefinder.android.model.Venue;

public class VenueListActivity extends ActionBarActivity
        implements VenueListFragment.Callbacks {

    private boolean mTwoPane;
    
    private Venue mVenue;
    
    private Menu mOptionsMenu;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_venue_list);

        if (findViewById(R.id.venue_detail_container) != null) {
            mTwoPane = true;
            ((VenueListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.venue_list))
                    .setActivateOnItemClick(true);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mOptionsMenu = menu;
    	if(mTwoPane){
            getMenuInflater().inflate(R.menu.action_bar_share_menu, mOptionsMenu);
            MenuItem item = mOptionsMenu.findItem(R.id.action_share);
            item.setVisible(false);
    	}
        return true;
    }
    
    private void updateMenu() {
        if (mOptionsMenu != null && mVenue != null && mTwoPane) {
            MenuItem item = mOptionsMenu.findItem(R.id.action_share);
            item.setVisible(true);
            ShareActionProvider myShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
            myShareActionProvider.setShareIntent(Global.createShareIntent(mVenue, getApplicationContext()));
        }
    }
    
    @Override
    public void onItemSelected(Venue v) {
    	mVenue = v;
    	updateMenu();
        if (mTwoPane) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.venue_detail_container, VenueDetailFragment.getInstance(v))
                    .commit();

        } else {
            Intent detailIntent = new Intent(this, VenueDetailActivity.class);
            detailIntent.putExtra(Venue.class.getName(), v);
            startActivity(detailIntent);
        }
    }
}
