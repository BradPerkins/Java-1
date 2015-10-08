//Bradley Perkins
// Project 2
// Java 1

package com.example.bradperkins.project1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.app.AppCompatActivity;

import java.util.HashSet;


/**
 * Created by bradperkins on 10/7/15.
 */
public class Alerts extends MainActivity{

    private static Context mContext;
    private static Context mName;
    private static Context applicationContext;


    public static void alertDialog1(final Context mContext, final String mName) {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
        alertDialog.setTitle("Saved");
        alertDialog.setMessage(mName + " comic is saved");
        alertDialog.setIcon(R.mipmap.ic_launcher);
        alertDialog.setPositiveButton("Yes", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(mContext, mName + " comic Added", Toast.LENGTH_SHORT).show();
            }
        });

        alertDialog.show();

    }

    public static void alertDialog2(final Context mContext, final String mName, final HashSet<String> mDataSet, final TextView mNumOfItems) {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
        alertDialog.setTitle("Saved");
        alertDialog.setMessage("You Indexed: ");
        alertDialog.setMessage(" " + mName + " ");
        alertDialog.setIcon(R.mipmap.ic_launcher);
        alertDialog.setNeutralButton("Yes", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(mContext, mName + " comic indexed", Toast.LENGTH_SHORT).show();
                //Calls update average method in the Main Activity file
                MainActivity.updateAvg();
            }
        });

        alertDialog.setPositiveButton("Remove", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(mContext, mName + " comic removed", Toast.LENGTH_SHORT).show();

                mDataSet.remove(mName);
                System.out.println(mDataSet);

                //resets the numOfItems Label
                mNumOfItems.setText(String.valueOf(mDataSet.size()));

                //Calls update average method in the Main Activity file
                MainActivity.updateAvg();

                //resets the text view when the array is empty
                if(dataSet.size() <= 0){
                    aveLength.setText("0");
                }

            }


        });
        alertDialog.show();
    }

    public static void removeToast(final Context mContext, final String mName){
        Toast.makeText(mContext, mName + " comic Removed", Toast.LENGTH_SHORT).show();
    }

    public Context getApplicationContext() {
        return applicationContext;
    }
}
