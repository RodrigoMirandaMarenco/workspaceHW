package com.rodrigo.venuefinder.android;

import android.content.Context;
import android.content.Intent;

import com.rodrigo.venuefinder.android.model.Venue;

public class Global {
	
    public static Intent createShareIntent(Venue venueItem, Context context) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, context.getString(R.string.name) + " " + venueItem.getName() + "; " + context.getString(R.string.address) + " " + getFullVenueAddress(venueItem, false));
        shareIntent.setType("text/plain");
        return shareIntent;
    }
    
    public static String getFullVenueAddress(Venue venueItem, boolean setZipCode){
    	String fullAddress = "";
    	if(venueItem.getAddress() != null)
    		fullAddress = venueItem.getAddress();
    	
    	String space;
    	if(setZipCode)
    		space = "\n";
    	else
    		space = ", ";

    	if(venueItem.getCity() != null)
    		fullAddress = fullAddress + space + venueItem.getCity();
    	if(venueItem.getState() != null)
    		fullAddress = fullAddress + ", " + venueItem.getState();
    	
    	if(setZipCode){
        	if(venueItem.getZip() != null)
        		fullAddress = fullAddress + " " + venueItem.getZip();	
    	}
    	
    	return fullAddress;
    }
    
}
