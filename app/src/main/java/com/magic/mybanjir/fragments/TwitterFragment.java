package com.magic.mybanjir.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import com.magic.mybanjir.MainActivity;
import com.magic.mybanjir.R;
import com.twitter.sdk.android.tweetui.CompactTweetView;
import com.twitter.sdk.android.tweetui.TweetViewFetchAdapter;

import java.util.ArrayList;
import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Created by admin on 12/26/14.
 */
public class TwitterFragment extends Fragment {

    private TweetViewFetchAdapter adapter;
    private SwipeRefreshLayout refreshLayout;
    private Long lastTweetId;
    private List<Long> tweetIds = new ArrayList<>();
    private boolean fetchMoreTweets;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        adapter = new TweetViewFetchAdapter<CompactTweetView>(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);

        View root = inflater.inflate(R.layout.fragment_tweets, null);
        ListView listView = (ListView) root.findViewById(R.id.lvTweets);
        refreshLayout = (SwipeRefreshLayout)root.findViewById(R.id.srlTweeter);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadNewTweets();
            }
        });

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(totalItemCount > 0 && totalItemCount - firstVisibleItem <= visibleItemCount + 4 && fetchMoreTweets){
//                    loadMoreTweets();
                }
            }
        });

        loadNewTweets();
        listView.setAdapter(adapter);

        return root;
    }

    private void loadNewTweets(){
        tweetIds = new ArrayList<>();
        lastTweetId = 0L;
        TwitterAsyncTask asyncTask = new TwitterAsyncTask();
        asyncTask.execute("myBanjir");
    }

    private void loadMoreTweets(){
        TwitterAsyncTask asyncTask = new TwitterAsyncTask();
        asyncTask.execute("myBanjir");
    }

    private class TwitterAsyncTask extends AsyncTask<String, Void, List<Status>> {

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            refreshLayout.setRefreshing(true);
            fetchMoreTweets = false;
        }

        @Override
        protected List<twitter4j.Status> doInBackground(String... params) {

            ConfigurationBuilder cb = new ConfigurationBuilder();
            cb.setOAuthConsumerKey(MainActivity.TWITTER_KEY)
                    .setOAuthConsumerSecret(MainActivity.TWITTER_SECRET)
                    .setApplicationOnlyAuthEnabled(true); // IMPORTANT: set T4J to use App-only auth
            TwitterFactory tf = new TwitterFactory(cb.build());
            Twitter twitter = tf.getInstance();

            Query query = new Query(params[0]);
            query.count(100);

            if(lastTweetId > 0L){
                query.setMaxId(lastTweetId);
            }

            QueryResult result;
            try {
                twitter.getOAuth2Token();
                result = twitter.search(query);
                return result.getTweets();
            } catch (twitter4j.TwitterException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<twitter4j.Status> statuses) {

            super.onPostExecute(statuses);

            refreshLayout.setRefreshing(false);
            fetchMoreTweets = true;

            if(statuses == null){
                return;
            }

            for (twitter4j.Status status : statuses) {
                tweetIds.add(status.getId());
            }

            lastTweetId = tweetIds.get(tweetIds.size() -1);

            adapter.setTweetIds(tweetIds);
            adapter.notifyDataSetChanged();
        }
    }
}
