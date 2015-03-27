package com.phunware.homework.android.fragments;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.phunware.homework.android.R;
import com.phunware.homework.android.adapters.VenueAdapter;
import com.phunware.homework.android.model.Schedule;
import com.phunware.homework.android.model.Venue;
import com.squareup.picasso.Picasso;

public class VenueDetailFragment extends Fragment {
	
    private Venue mVenue;

    public VenueDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        if (getArguments().containsKey(Venue.class.getName())) {
        	mVenue = getArguments().getParcelable(Venue.class.getName());;
        }
    }
    
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    	super.onCreateOptionsMenu(menu, inflater);
    	inflater.inflate(R.menu.action_bar_share_menu, menu);
    	if (menu != null && mVenue != null) {
            MenuItem item = menu.findItem(R.id.action_share);
            item.setVisible(true);
            ShareActionProvider myShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.name) + " " + mVenue.getName() + "; " + getString(R.string.address) + " " + VenueAdapter.getFullVenueAddress(mVenue, false));
            shareIntent.setType("text/plain");
            myShareActionProvider.setShareIntent(shareIntent);
        }
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	setHasOptionsMenu(true);
        View rootView = inflater.inflate(R.layout.fragment_venue_detail, container, false);

        ImageView imgVenue = ((ImageView) rootView.findViewById(R.id.img_venue));
        
        if (mVenue != null) {
    		if(mVenue.getImageUrl().length() > 0){
                Picasso.with(getActivity().getApplicationContext())
                .load(mVenue.getImageUrl())
                .placeholder(R.drawable.gray)
                .into(imgVenue);
    		}else{
    			imgVenue.setImageResource(R.drawable.imagenotfound);
    		}
    		
    		StringBuilder venueSchedules = new StringBuilder();

            for(Schedule schedule:mVenue.getSchedule()){
            	if(schedule.getEndDate().length() > 0 && schedule.getStartDate().length() > 0){
            		
            	}
            	venueSchedules.append("\n\n" + getFormattedDate(schedule.getStartDate(), "EEEE M/dd hh:mma") + " to " + getFormattedDate(schedule.getEndDate(), "hh:mma")); 
            }
            
            ((TextView) rootView.findViewById(R.id.txt_venue_description)).setText(mVenue.getName());
            ((TextView) rootView.findViewById(R.id.txt_venue_address)).setText(VenueAdapter.getFullVenueAddress(mVenue, true));
            if(venueSchedules.length() > 0){
            	((TextView) rootView.findViewById(R.id.txt_venue_detail)).setText(venueSchedules.toString());	
            }
            if (((TelephonyManager)getActivity().getSystemService(Context.TELEPHONY_SERVICE)).getPhoneType()
            	    != TelephonyManager.PHONE_TYPE_NONE && mVenue.getPhone() != null){
            	if(mVenue.getPhone().length() > 0){
            		((Button) rootView.findViewById(R.id.btn_call)).setVisibility(View.VISIBLE);
                    ((Button) rootView.findViewById(R.id.btn_call)).setOnClickListener(new OnClickListener() {
    					@Override
    					public void onClick(View arg0) {
    						String uri = "tel:"+mVenue.getPhone();
    						Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse(uri));							                     
                        	startActivity(dialIntent);
    					}
    				});	
            	}   
            }
            if(mVenue.getTicketLink() != null){
            	if(mVenue.getTicketLink().length() > 0){
            		((Button) rootView.findViewById(R.id.btn_website)).setVisibility(View.VISIBLE);
            		((Button) rootView.findViewById(R.id.btn_website)).setOnClickListener(new OnClickListener() {
    					@Override
    					public void onClick(View arg0) {
    						Intent dialIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mVenue.getTicketLink()));							                     
                        	startActivity(dialIntent);
    					}
    				});
            	}
            }
        }

        return rootView;
    }
    
    private String getFormattedDate(String dateGMT, String format){
    	SimpleDateFormat dateGMTFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z", Locale.getDefault());
    	SimpleDateFormat finalFormat = new SimpleDateFormat(format, Locale.getDefault());
    	String formattedDate = null;
    	Date convertedDate = new Date();
	     try {
			convertedDate = dateGMTFormat.parse(dateGMT);
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
	    formattedDate = finalFormat.format(convertedDate);
    	return formattedDate; 
    }

	public static Fragment getInstance(Venue venue) {
    	VenueDetailFragment fragment = new VenueDetailFragment();
    	Bundle b = new Bundle();
    	b.putParcelable(Venue.class.getName(), venue);
    	fragment.setArguments(b);
		return fragment;
	}
    
}
