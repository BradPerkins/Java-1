
package com.example.bradperkins.project_4;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

static String finalString = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ProgressBar progress_bar = (ProgressBar)findViewById(R.id.progress_bar);

        //Progress Bar Invisible
        progress_bar.setVisibility(View.INVISIBLE);//Visible in the getApi method

        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final EditText etMovie = (EditText) findViewById(R.id.movie_et);
                String etMovieText = URLEncoder.encode(etMovie.getText().toString());
                String newString1 = "http://api.rottentomatoes.com/api/public/v1.0/movies.json?apikey=p9twynar8pn93xbw8az5f5px&q=";
                String newString2 = "&page_limit=1";

                finalString = newString1 + etMovieText + newString2 + "";

                if (etMovieText.isEmpty()) {
                    etMovie.setError("Please enter a movie title.");
                } else {
                    etMovie.getText().clear();
                    //Dismiss Keypad
                    etMovie.onEditorAction(EditorInfo.IME_ACTION_DONE);
                }
                getApi();
            }
        });

    }

    //Method that Checks for Connectivity
    private void getApi() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null){
            ProgressBar progress_bar = (ProgressBar)findViewById(R.id.progress_bar);
            //Set progress bar to visible
            progress_bar.setVisibility(View.VISIBLE);

            NetworkInfo info = cm.getActiveNetworkInfo();
            if (info != null && info.isAvailable()){
                //If Network is on
                MovieDataTask task = new MovieDataTask();
                task.execute();

            }else {
                //No connection Alert
                Toast.makeText(this, "No Network", Toast.LENGTH_LONG).show();
            }
        }
    }

    public AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

            Movie movie = (Movie)adapterView.getAdapter().getItem(position);

            final TextView titleTV = (TextView)findViewById(R.id.title_tv);
            final TextView yearTV = (TextView)findViewById(R.id.year_tv);
            final TextView synTV = (TextView)findViewById(R.id.synopsis_tv);

            //Pulls data and sets to Strings to fill textviews
            String movieName = Movie.getName();
            titleTV.setText(movieName);

            String movieYear = Movie.getYear();
            yearTV.setText(movieYear);

            String movieSyn = Movie.getSyn();
            synTV.setText(movieSyn);


        }
    };

    private class MovieDataTask extends AsyncTask<Void, Void, ArrayList<Movie>>{

        @Override
        protected ArrayList<Movie> doInBackground(Void... params) {
            //parse in the background
            String data = Util.getMovieData();
            ArrayList<Movie> movies = Util.parseMovieFromFeed(data);

            return movies;
        }

        @Override
        protected void onPostExecute(ArrayList<Movie> movies) {
            super.onPostExecute(movies);

            //UI Operations
            ListView list = (ListView)findViewById(R.id.list);
            MovieAdapter adapter = new MovieAdapter(MainActivity.this, movies);
            list.setAdapter(adapter);
            //Listener
            list.setOnItemClickListener(listener);

            //Progress bar disapears after data loaded.
            ProgressBar progress_bar = (ProgressBar)findViewById(R.id.progress_bar);
            progress_bar.setVisibility(View.INVISIBLE);

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }
    }
}