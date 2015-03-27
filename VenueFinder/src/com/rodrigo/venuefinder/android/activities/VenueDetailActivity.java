package com.rodrigo.venuefinder.android.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;

import com.rodrigo.venuefinder.android.R;
import com.rodrigo.venuefinder.android.fragments.VenueDetailFragment;
import com.rodrigo.venuefinder.android.model.Venue;

public class VenueDetailActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        
        if (savedInstanceState == null) {
        	Venue venue = (Venue) getIntent().getParcelableExtra(Venue.class.getName());
            getSupportFragmentManager().beginTransaction()
            		.add(R.id.venue_detail_container, VenueDetailFragment.getInstance(venue))
                	.commit();
        }
    }
    
    public VenueDetailActivity(){
    	
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
        	finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    public static Intent getInstance(Venue venue, Context context) {
    	Intent detailIntent = new Intent(context, VenueDetailActivity.class);
        detailIntent.putExtra(Venue.class.getName(), venue);
		return detailIntent;
	}
    
}
