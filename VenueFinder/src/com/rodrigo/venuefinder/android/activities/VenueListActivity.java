package com.rodrigo.venuefinder.android.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.rodrigo.venuefinder.android.R;
import com.rodrigo.venuefinder.android.fragments.VenueDetailFragment;
import com.rodrigo.venuefinder.android.fragments.VenueListFragment;
import com.rodrigo.venuefinder.android.model.Venue;

public class VenueListActivity extends ActionBarActivity
        implements VenueListFragment.Callbacks {

    private boolean mTwoPane;
 
    private VenueListFragment mVenueListFragment;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_venue_list);

        if (savedInstanceState == null) {
        	mVenueListFragment = new VenueListFragment();
            getSupportFragmentManager().beginTransaction()
            		.add(R.id.venue_list_container, mVenueListFragment)
                	.commit();
        }else{
        	mVenueListFragment = ((VenueListFragment) getSupportFragmentManager().findFragmentById(R.id.venue_list_container));
        }
        
    }
	
	@Override
	protected void onResume() {
		super.onResume();
		if (findViewById(R.id.venue_detail_container) != null && !mTwoPane) {
            mTwoPane = true;
            mVenueListFragment.setActivateOnItemClick(true);
        }
	}
    
    @Override
    public void onItemSelected(Venue v) {
        if (mTwoPane) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.venue_detail_container, VenueDetailFragment.getInstance(v))
                    .commit();
        } else {
            startActivity(VenueDetailActivity.getInstance(v, this));
        }
    }
}
