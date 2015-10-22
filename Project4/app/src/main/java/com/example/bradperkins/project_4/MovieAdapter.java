package com.example.bradperkins.project_4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by bradperkins on 10/20/15.
 */
public class MovieAdapter extends BaseAdapter{

    public static final int ID_CONSTANT = 0x02010201;

    Context mContext;
    ArrayList<Movie> mMovies;

    public MovieAdapter(Context _c, ArrayList<Movie> _teams){
        mContext = _c;
        mMovies = _teams;
    }

    @Override
    public int getCount() {
        if (mMovies == null){
            return 0;
        }
        return mMovies.size();
    }

    @Override
    public Object getItem(int position) {
        if (mMovies == null || position >= mMovies.size()){
            return null;
        }
        return mMovies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return ID_CONSTANT + position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(android.R.layout.simple_list_item_2, parent, false);
        }

        Movie movie = (Movie) getItem(position);

        if(movie != null){
            TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
            tv.setText(movie.getName());

            tv = (TextView) convertView.findViewById(android.R.id.text2);
            tv.setText(movie.getYear());

        }
        return convertView;
    }
}
