package com.rodrigo.venuefinder.android;

import android.content.Context;
import android.content.Intent;

import com.rodrigo.venuefinder.android.model.Venue;

public class Global {
	
    public static Intent createShareIntent(Venue venueItem, Context context) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, context.getString(R.string.name) + " " + venueItem.getName() + "; " + context.getString(R.string.address) + " " + venueItem.getAddress());
        shareIntent.setType("text/plain");
        return shareIntent;
    }
    
}