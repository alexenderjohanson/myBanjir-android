package com.magic.mybanjir.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.magic.mybanjir.Provider.NewsProvider;
import com.magic.mybanjir.R;
import com.magic.mybanjir.activities.WebviewActivity;
import com.magic.mybanjir.adapters.NewsAdapter;
import com.magic.mybanjir.models.Item;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by admin on 12/26/14.
 */
public class NewsFragment extends Fragment {

    private NewsProvider newsProvider;
    private SwipeRefreshLayout refreshLayout;
    private NewsAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://www.mybanjir.com")
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();


        newsProvider = restAdapter.create(NewsProvider.class);

        adapter = new NewsAdapter(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);

        View root = inflater.inflate(R.layout.fragment_tweets, null);
        ListView listView = (ListView) root.findViewById(R.id.lvTweets);
        refreshLayout = (SwipeRefreshLayout) root.findViewById(R.id.srlTweeter);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                loadNews();
            }
        });

        listView.setAdapter(adapter);
        listView.setDivider(null);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Item item = adapter.getItem(position);
                Intent intent = new Intent(getActivity(), WebviewActivity.class);
                intent.putExtra("url", item.url);
                intent.putExtra("title", item.title);

                startActivity(intent);
            }
        });

        loadNews();

        return root;
    }

    private void loadNews() {

        newsProvider.getNews(new Callback<List<Item>>() {
            @Override
            public void success(List<Item> items, Response response) {

                adapter.setNews(items);
                refreshLayout.setRefreshing(false);
            }

            @Override
            public void failure(RetrofitError error) {

                refreshLayout.setRefreshing(false);
                Log.e("getNews", error.getMessage());
                error.printStackTrace();
            }
        });
    }
}
