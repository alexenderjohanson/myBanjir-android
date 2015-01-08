package com.magic.mybanjir.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.magic.mybanjir.Provider.RiverLevelProvider;
import com.magic.mybanjir.R;
import com.magic.mybanjir.activities.RiverLevelActivity;
import com.magic.mybanjir.adapters.StateAdapter;

import retrofit.RestAdapter;

/**
 * Created by admin on 12/26/14.
 */
public class RiverLevelFragment extends Fragment {

    RiverLevelProvider riverLevelProvider;
    StateAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://www.mybanjir.com")
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();


        riverLevelProvider = restAdapter.create(RiverLevelProvider.class);

        adapter = new StateAdapter(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);

        View root = inflater.inflate(R.layout.fragment_tweets, null);
        ListView listView = (ListView) root.findViewById(R.id.lvTweets);
        SwipeRefreshLayout srl = (SwipeRefreshLayout)root.findViewById(R.id.srlTweeter);

        srl.setEnabled(false);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                StateAdapter.State state = adapter.getItem(position);
                Intent intent = new Intent(getActivity(), RiverLevelActivity.class);
                intent.putExtra("stateName", state.name);
                intent.putExtra("stateCode", state.code);

                startActivity(intent);
            }
        });

        return root;
    }
}
