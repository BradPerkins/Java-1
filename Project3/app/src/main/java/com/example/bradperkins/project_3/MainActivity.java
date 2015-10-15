package com.example.bradperkins.project_3;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayList<Teams> teams = new ArrayList<>();

        //Adds Data
        teams.add(new Teams("Bengals", "5-0", R.mipmap.bengals));
        teams.add(new Teams("Browns", "2-3", R.mipmap.browns));
        teams.add(new Teams("Steelers", "3-2", R.mipmap.steelers));
        teams.add(new Teams("Ravens", "1-4", R.mipmap.ravens));

        //Checks Orientation
        int orientation=this.getResources().getConfiguration().orientation;
        if(orientation==Configuration.ORIENTATION_PORTRAIT){

            //code for portrait mode
            //Pulls layout for Portrait Mode
            setContentView(R.layout.activity_main);

            Spinner spinner = (Spinner)findViewById(R.id.spinner);

            //Set up a Adapter for Spinner
            ArrayAdapter<String> spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, teams);

            //sets Adapter
            spinner.setAdapter(spinnerAdapter);

            //Spinner OnItemSelectedListener
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                    Teams teams = (Teams)adapterView.getAdapter().getItem(position);

                    final TextView portNameTV = (TextView)findViewById(R.id.port_name_tv);
                    final TextView portRecordTV = (TextView)findViewById(R.id.port_record_tv);
                    final ImageView portLogoIV = (ImageView)findViewById(R.id.port_logo_iv);

                    //Pulls Data and sets them to Strings to fill the Text Views and Image View.
                    String teamName = teams.getName();
                    portNameTV.setText(teamName);

                    String record = teams.getRecord().toString();
                    portRecordTV.setText(record);

                    int logo = teams.getLogo();
                    portLogoIV.setImageResource(logo);
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        }
        else{

            //code for landscape mode
            setContentView(R.layout.activity_main_land);//landscape mode

            ListView teamList = (ListView)findViewById(R.id.teamsList);
            teamList.setOnItemClickListener(listener);

            //Impliments the Custom Adapter TeamsAdapter
            TeamsAdapter adapter = new TeamsAdapter(this, teams);

            teamList.setAdapter(adapter);
        }

    }

    //Adapter View OnClicklistener
    public AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            Teams teams = (Teams)adapterView.getAdapter().getItem(position);

            final TextView nameTV = (TextView)findViewById(R.id.name_tv);
            final TextView recordTV = (TextView)findViewById(R.id.record_tv);
            final ImageView logoIV = (ImageView)findViewById(R.id.logo_iv);

            //Pulls Data and sets them to Strings to fill the Text Views and Image View.
            String teamName = teams.getName();
            nameTV.setText(teamName);

            String record = teams.getRecord().toString();
            recordTV.setText(record);

            int logo = teams.getLogo();
            logoIV.setImageResource(logo);

        }
    };

}
