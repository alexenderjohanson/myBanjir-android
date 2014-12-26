package com.magic.mybanjir.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.magic.mybanjir.R;
import com.magic.mybanjir.models.Item;
import com.magic.mybanjir.utils.TimeUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 12/26/14.
 */
public class NewsAdapter extends BaseAdapter {

    private Context context;
    private List<Item> news;

    public NewsAdapter(Context context) {
        this.context = context;
        news = new ArrayList<>();
    }

    @Override
    public int getCount() {

        return news.size();
    }

    @Override
    public Item getItem(int position) {

        return news.get(position);
    }

    @Override
    public long getItemId(int position) {

        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = View.inflate(context, R.layout.cell_news, null);
        }

        TextView title = (TextView)convertView.findViewById(R.id.tvTitle);
        TextView body = (TextView)convertView.findViewById(R.id.tvBody);
        TextView footnote = (TextView)convertView.findViewById(R.id.tvFootNote);
        ImageView imageView = (ImageView)convertView.findViewById(R.id.ivImage);

        Item item = news.get(position);

        title.setText(item.title);
        body.setText(item.body);
        if(item.description != null && item.description.length() > 0){
            body.setText(item.description);
        }

        if(body.getText().toString().length() < 1){
            body.setVisibility(View.GONE);
        } else {
            body.setVisibility(View.VISIBLE);
        }

        if(item.image != null && item.image.length() > 0){
            imageView.setVisibility(View.VISIBLE);
            Picasso.with(context).load(item.image).into(imageView);
        } else {
            imageView.setVisibility(View.GONE);
        }

        StringBuilder builder = new StringBuilder();
        builder.append(TimeUtils.getTimePassed(item.getDateTime().getTime()));
        builder.append(" / " + item.soruce);

        if(item.location != null && item.location.length() > 0){
            builder.append(" / " + item.location);
        }

        footnote.setText(builder.toString());

        return convertView;
    }

    public void setNews(List<Item> news){

        this.news = news;
        notifyDataSetChanged();
    }
}
