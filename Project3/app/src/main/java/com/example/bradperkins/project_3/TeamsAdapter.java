package com.example.bradperkins.project_3;

/**
 * Created by bradperkins on 10/14/15.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

//Custom Adapter

public class TeamsAdapter extends BaseAdapter{

    private static final int ID_CONSTANT = 0x01010101;
    private Context mContext;
    private ArrayList<Teams> mTeams;

    public TeamsAdapter(Context _context, ArrayList<Teams> _teams){
        mContext = _context;
        mTeams = _teams;


    }

    @Override
    public int getCount() {
        //Items in collection
        if (mTeams == null){
            return 0;
        }
        return mTeams.size();

    }

    @Override
    public Teams getItem(int position) {
        if (mTeams != null && position < mTeams.size() && position >= 0){
            return mTeams.get(position);
        }
            return null;
    }

    @Override
    public long getItemId(int position) {
        return ID_CONSTANT + position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_layout, parent, false);
        }

        Teams teams = getItem(position);

        if (teams != null){
            ImageView iv = (ImageView)convertView.findViewById(R.id.team_logo);
            iv.setImageResource(teams.getLogo());

            TextView tv =(TextView)convertView.findViewById(R.id.team_name);
            tv.setText(teams.getName());
            tv = (TextView)convertView.findViewById(R.id.team_record);
            tv.setText(teams.getRecord());

        }

        return convertView;
    }
}
