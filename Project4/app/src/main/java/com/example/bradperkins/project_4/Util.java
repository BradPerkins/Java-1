package com.example.bradperkins.project_4;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by bradperkins on 10/20/15.
 */
public class Util extends MainActivity{

    public static String getMovieData(){

        final String FEED_URL = finalString;

        //Grabs Movie DATA
        try {
            URL url = new URL(FEED_URL);

            URLConnection connection = (HttpURLConnection)url.openConnection();
            connection.connect();
            InputStream is = connection.getInputStream();
            String data = IOUtils.toString(is);
            is.close();
            //connection.disconnect();
            return data;

        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    // Pulls Data from the API
    public static ArrayList<Movie> parseMovieFromFeed(String _feedData){
        ArrayList<Movie> movies = new ArrayList<>();

        try{
            JSONObject obj = new JSONObject(_feedData);
            JSONArray moviesArray = obj.getJSONArray("movies"); //JSON ARRAY
            for (int i = 0; i < moviesArray.length(); i++){
                JSONObject movieObj = moviesArray.getJSONObject(i);
                String name = movieObj.getString("title"); //object name
                String year = movieObj.getString("year"); //object
                String pos = movieObj.getString("synopsis"); //object

                movies.add(new Movie(name, year, pos));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return movies;
    }
}
