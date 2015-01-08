package com.magic.mybanjir.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.magic.mybanjir.R;
import com.magic.mybanjir.models.RiverLevel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 12/26/14.
 */
public class RiverLevelAdapter extends BaseAdapter {

    private List<RiverLevel> riverLevels;
    private Context context;

    public RiverLevelAdapter(Context context){
        this.context = context;
        riverLevels = new ArrayList<>();
    }

    @Override
    public int getCount() {

        return riverLevels.size();
    }

    @Override
    public RiverLevel getItem(int position) {

        return riverLevels.get(position);
    }

    @Override
    public long getItemId(int position) {

        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = View.inflate(context, R.layout.cell_river_level, null);
        }

        LinearLayout cardView = (LinearLayout)convertView.findViewById(R.id.llRiverLevel);
        TextView station = (TextView)convertView.findViewById(R.id.tvStation);
        TextView district = (TextView)convertView.findViewById(R.id.tvDistrict);
        TextView basin = (TextView)convertView.findViewById(R.id.tvBasin);

        RiverLevel riverLevel = riverLevels.get(position);

        if(riverLevel.riverLevel >= riverLevel.dangerLevel){
            cardView.setBackgroundColor(context.getResources().getColor(R.color.red));
            basin.setText(R.string.river_level_danger);
        } else if(riverLevel.riverLevel >= riverLevel.warningLevel){
            cardView.setBackgroundColor(context.getResources().getColor(R.color.orange));
            basin.setText(R.string.river_level_warning);
        } else if (riverLevel.riverLevel >= riverLevel.alertLevel){
            cardView.setBackgroundColor(context.getResources().getColor(R.color.light_yellow));
            basin.setText(R.string.river_level_alert);
        } else if (riverLevel.riverLevel > 0){
            cardView.setBackgroundColor(context.getResources().getColor(R.color.green));
            basin.setText(R.string.river_level_normal);
        } else {
            cardView.setBackgroundColor(context.getResources().getColor(R.color.extra_light_gray));
            basin.setText(R.string.river_level_normal);
        }

        station.setText(riverLevel.stationName);
        district.setText(riverLevel.district);

        basin.setText(basin.getText() + " / " + riverLevel.riverLevel);

        return convertView;
    }

    public void setRiverLevels(List<RiverLevel> riverLevels) {

        this.riverLevels = riverLevels;
        notifyDataSetChanged();
    }
}
