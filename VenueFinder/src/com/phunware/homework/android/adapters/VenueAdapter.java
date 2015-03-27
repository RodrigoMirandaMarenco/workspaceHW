package com.phunware.homework.android.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.phunware.homework.android.R;
import com.phunware.homework.android.model.Venue;

public class VenueAdapter extends ArrayAdapter<Venue> {
    
	private LayoutInflater mInflater;

    public VenueAdapter(Context context, int textViewResourceId, List<Venue> objects) {
        super(context, textViewResourceId, objects);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        Holder holder;

        if (view == null) {
            view = mInflater.inflate(R.layout.grid_item, parent, false);
            holder = new Holder();
            holder.name = (TextView) view.findViewById(R.id.txt_venue_name);
            holder.address = (TextView) view.findViewById(R.id.txt_venue_address);
            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }

        Venue venue = getItem(position);
        holder.name.setText(venue.getName());
        holder.address.setText(getFullVenueAddress(venue, false));

        return view;
    }

    private static final class Holder {
        public TextView name;
        public TextView address;
    }
    
    /**
     * Returns a full address string using all the venue's details.
     * <p>
     * If the setZipCode parameter is false, a new line is added to the  
     * string instead of the Zip Code.
     *
     * @param  venueItem  the venue to get the address from.
     * @param  setZipCode whether or not to set the Zip Code.
     * @return      the full address string.
     */
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
