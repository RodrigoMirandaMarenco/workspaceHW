package com.rodrigo.venuefinder.android.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.rodrigo.venuefinder.android.Global;
import com.rodrigo.venuefinder.android.R;
import com.rodrigo.venuefinder.android.model.Venue;

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
        holder.address.setText(Global.getFullVenueAddress(venue, false));

        return view;
    }

    private static final class Holder {
        public TextView name;
        public TextView address;
    }
}
