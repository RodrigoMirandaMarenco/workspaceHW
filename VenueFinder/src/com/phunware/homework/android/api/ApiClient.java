package com.phunware.homework.android.api;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Query;

import com.phunware.homework.android.model.Venue;

public class ApiClient {
    
	private static VenuesApiInterface sVenuesService;

    public static VenuesApiInterface getVenuesApiClient() {
        if (sVenuesService == null) {
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint("https://s3.amazonaws.com")
                    .build();
            sVenuesService = restAdapter.create(VenuesApiInterface.class);
        }
        return sVenuesService;
    }
    
    public interface VenuesApiInterface {
    	@GET("/jon-hancock-phunware/nflapi-static.json")
        void getVenues(@Query("limit") int limit, @Query("offset") int offset, Callback<List<Venue>> callback);
    }
    
}
