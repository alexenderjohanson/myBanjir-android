package com.magic.mybanjir.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.magic.mybanjir.Provider.RiverLevelProvider;
import com.magic.mybanjir.R;
import com.magic.mybanjir.adapters.RiverLevelAdapter;
import com.magic.mybanjir.models.RiverLevel;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by admin on 12/26/14.
 */
public class RiverLevelActivity extends ActionBarActivity {


    private RiverLevelProvider provider;
    private ListView listView;
    private RiverLevelAdapter adapter;
    private TextView status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_river_level);

        listView = (ListView)findViewById(R.id.lvRiverLevel);
        Toolbar toolbar = (Toolbar)findViewById(R.id.tbToolbar);
        status = (TextView)findViewById(R.id.tvStatus);

        String name = getIntent().getStringExtra("stateName");
        String code = getIntent().getStringExtra("stateCode");

        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(name);


        adapter = new RiverLevelAdapter(this);
        listView.setAdapter(adapter);

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://www.mybanjir.com")
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        provider = restAdapter.create(RiverLevelProvider.class);

        provider.getRiverLevel(code, new Callback<List<RiverLevel>>() {
            @Override
            public void success(List<RiverLevel> items, Response response) {

                if(items == null || items.size() < 1){
                    status.setText(R.string.river_level_unavailable);
                    return;
                }
                adapter.setRiverLevels(items);
                status.setVisibility(View.GONE);
            }

            @Override
            public void failure(RetrofitError error) {
                status.setText(R.string.river_level_unavailable);
            }
        });

        status.setText(R.string.common_loading);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
