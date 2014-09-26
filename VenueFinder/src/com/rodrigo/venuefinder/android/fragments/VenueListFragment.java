package com.rodrigo.venuefinder.android.fragments;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

import com.rodrigo.venuefinder.android.adapters.VenueAdapter;
import com.rodrigo.venuefinder.android.api.ApiClient;
import com.rodrigo.venuefinder.android.model.Venue;

public class VenueListFragment extends ListFragment {

    private static final int ITEMS_PER_PAGE = 50;
    
    private static final String STATE_ACTIVATED_POSITION = "activated_position";

    private static class ActivityState {
        private int nextPage = 0;

        private List<Venue> venueData = new ArrayList<Venue>();
    }
    
    private boolean mIsDownloadInProgress = false;

    private ActivityState mState = new ActivityState();

    private Callbacks mCallbacks = sDummyCallbacks;

    private int mActivatedPosition = ListView.INVALID_POSITION;
    
    private VenueAdapter mAdapter;

    public interface Callbacks {
        public void onItemSelected(Venue v);
    }

    private static Callbacks sDummyCallbacks = new Callbacks() {
        @Override
        public void onItemSelected(Venue v) {
        }
    };

    public VenueListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new VenueAdapter(getActivity().getApplicationContext(), 0, mState.venueData);
        setListAdapter(mAdapter);
    }
    
    @Override
    public void onResume() {
        super.onResume();
        if (mState.nextPage == 0) {
            downloadData(mState.nextPage);
        }
    }

    private void downloadData(final int pageNumber) {
        if (!mIsDownloadInProgress) {
            mIsDownloadInProgress = true;
            setListShown(false);
            ApiClient.getVenuesApiClient().getVenues(ITEMS_PER_PAGE, pageNumber * ITEMS_PER_PAGE, new Callback<List<Venue>>() {
                @Override
                public void success(List<Venue> venuesData, Response response) {
                    consumeApiData(venuesData);
                }

                @Override
                public void failure(RetrofitError retrofitError) {
                    consumeApiData(null);
                }
            });
        }
    }

    private void consumeApiData(List<Venue> venuesData) {
        if (venuesData != null) {
            mState.venueData.addAll(venuesData);
            mAdapter.notifyDataSetChanged();
            mState.nextPage++;
            try {
                setListShown(true);
			} catch (IllegalStateException e) {
				venuesData = null;
			}
        }
        mIsDownloadInProgress = false;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState != null
                && savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
            setActivatedPosition(savedInstanceState.getInt(STATE_ACTIVATED_POSITION));
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (!(activity instanceof Callbacks)) {
            throw new IllegalStateException("Activity must implement fragment's callbacks.");
        }
        mCallbacks = (Callbacks) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = sDummyCallbacks;
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);
        mCallbacks.onItemSelected(mAdapter.getItem(position));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mActivatedPosition != ListView.INVALID_POSITION) {
            outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
        }
    }

    public void setActivateOnItemClick(boolean activateOnItemClick) {
        getListView().setChoiceMode(activateOnItemClick
                ? ListView.CHOICE_MODE_SINGLE
                : ListView.CHOICE_MODE_NONE);
    }

    private void setActivatedPosition(int position) {
        if (position == ListView.INVALID_POSITION) {
            getListView().setItemChecked(mActivatedPosition, false);
        } else {
            getListView().setItemChecked(position, true);
        }

        mActivatedPosition = position;
    }
}
