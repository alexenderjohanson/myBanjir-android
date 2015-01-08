package com.magic.mybanjir.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.magic.mybanjir.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 12/26/14.
 */
public class StateAdapter extends BaseAdapter{

    /**
     Perlis : PEL
     Kedah : KDH
     Pulau Pinang : PNG
     Perak : PRK

     Selangor : SEL
     KL : WLH
     Negeri Sembilan : NSN
     Melaka : MLK
     Johor : JHR
     Pahang : PHG
     Terengganu : TRG
     Kelantan : KEL
     Sarawak : SRK
     Sabah : SAB
     */

    private List<State> states;
    private Context context;

    public StateAdapter(Context context){

        this.context = context;

        states = new ArrayList<>();
        states.add(new State("Perlis", "PLS"));
        states.add(new State("Kedah", "KDH"));
        states.add(new State("Pulau Pinang", "PNG"));
        states.add(new State("Perak", "PRK"));
        states.add(new State("Selangor", "SEL"));
        states.add(new State("KL", "WLH"));
        states.add(new State("Negeri Sembilan", "NSN"));
        states.add(new State("Melaka", "MLK"));
        states.add(new State("Johor", "JHR"));
        states.add(new State("Pahang", "PHG"));
        states.add(new State("Terengganu", "TRG"));
        states.add(new State("Kelantan", "KEL"));
        states.add(new State("Sarawak", "SRK"));
        states.add(new State("Sabah", "SAB"));
    }

    @Override
    public int getCount() {

        return 14;
    }

    @Override
    public State getItem(int position) {

        return states.get(position);
    }

    @Override
    public long getItemId(int position) {

        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = View.inflate(context, R.layout.cell_state, null);
        }

        TextView tvstate = (TextView)convertView.findViewById(R.id.tvState);

        State state = states.get(position);

        tvstate.setText(state.name);

        return convertView;
    }

    public class State implements Serializable{

        public String name;
        public String code;

        public State(String name, String code) {

            this.name = name;
            this.code = code;
        }
    }
}
