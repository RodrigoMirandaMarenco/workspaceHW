package com.rodrigo.venuefinder.android.fragments;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.rodrigo.venuefinder.android.R;
import com.rodrigo.venuefinder.android.model.Schedule;
import com.rodrigo.venuefinder.android.model.Venue;
import com.squareup.picasso.Picasso;

public class VenueDetailFragment extends Fragment {

    public static final String ARG_ITEM_ID = "item_id";
    
    public static final String ARG_VENUE_OBJECT = "venue_object";

    private Venue mItem;

    public VenueDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey(ARG_VENUE_OBJECT)) {
        	mItem = new Gson().fromJson(getArguments().getString(ARG_VENUE_OBJECT), Venue.class);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_venue_detail, container, false);

        ImageView imgVenue = ((ImageView) rootView.findViewById(R.id.img_venue));
        
        if (mItem != null) {
    		if(mItem.getImageUrl().length() > 0){
                Picasso.with(getActivity().getApplicationContext())
                .load(mItem.getImageUrl())
                .placeholder(R.drawable.transparent)
                .into(imgVenue);
    		}else{
    			imgVenue.setVisibility(View.GONE);
    		}
    		
    		StringBuilder venueSchedules = new StringBuilder();

            for(Schedule schedule:mItem.getSchedule()){
            	if(schedule.getEndDate().length() > 0 && schedule.getStartDate().length() > 0){
            		
            	}
            	venueSchedules.append("\n\n" + getFormattedDate(schedule.getStartDate(), "EEEE M/dd hh:mma") + " to " + getFormattedDate(schedule.getEndDate(), "hh:mma")); 
            }
            
            ((TextView) rootView.findViewById(R.id.txt_venue_description)).setText(mItem.getName());
            ((TextView) rootView.findViewById(R.id.txt_venue_address)).setText(mItem.getAddress());
            if(venueSchedules.length() > 0){
            	((TextView) rootView.findViewById(R.id.txt_venue_detail)).setText(venueSchedules.toString());	
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
    
}