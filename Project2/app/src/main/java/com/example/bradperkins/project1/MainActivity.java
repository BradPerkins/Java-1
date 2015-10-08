
//Bradley Perkins
// Project 2
// Java 1

package com.example.bradperkins.project1;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    EditText etComic;
    static TextView numOfItems;
    static TextView aveLength;
    EditText etIndex;


    static HashSet<String> dataSet = new HashSet<>();
    List<String> list = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public static void updateAvg(){

        Double lengthAvg = 0.0;
        for(String lengthNum : dataSet){
            lengthAvg = lengthAvg + lengthNum.length();

            //Sets the Number of items in the array in "Number of Items" label
            numOfItems.setText(String.valueOf(dataSet.size()));
            //String stringLength = String.valueOf(lengthAvg / dataSet.size());
            Double stringLength = (lengthAvg / dataSet.size());

            //Returns only 2 decimal points
            stringLength = Double.parseDouble(new DecimalFormat("##.##").format(stringLength));

            String newString = String.valueOf(stringLength);

            //Sets the Number for the "Average Length" Label
            aveLength.setText(newString);


        }
        return;
    }

    public void saveBtn(View view) {

        etComic = (EditText) findViewById(R.id.etComic);
        numOfItems = (TextView) findViewById(R.id.numOfItems);
        aveLength = (TextView) findViewById(R.id.aveLength);

        etComic.requestFocusFromTouch();

        String etComicString = etComic.getText().toString();

        int dataSetInt = dataSet.size();
        String dataSetTotal = String.valueOf(dataSetInt);

        //error for empty text
        if (etComicString.isEmpty()) {
            etComic.setError("Please enter a Comic");
        } else {

            numOfItems.setText(String.valueOf(dataSet.size()));
            dataSet.add(etComic.getText().toString());

            etComic.getText().clear();

            //Dismiss Keypad
            etComic.onEditorAction(EditorInfo.IME_ACTION_DONE);

            //Finds the Average Length of words in array
            //Average Method call
            updateAvg();

        }

        //Alert Dialog with app icon
        Alerts.alertDialog1(this, etComicString);

        //Changes textView color to orange
        int color = ChangeColor.getColor();
        etComic.setBackgroundColor(color);
    }

    public  void upDateNumOfItems(){
        numOfItems.setText(String.valueOf(dataSet.size()));
        return;
    }


    public void indexButton(View view) {

        etIndex = (EditText) findViewById(R.id.etIndex);
        String etIndexString = etIndex.getText().toString();
        final String[] newArray = dataSet.toArray(new String[dataSet.size()]);
        final int numEntered = Integer.valueOf(String.valueOf(etIndex.getText()));


        String text = etIndex.getText().toString().trim();


        if (numEntered < newArray.length ) {

            //Alert Dialog
            Alerts.alertDialog2(this, newArray[numEntered].toString(), dataSet, numOfItems);

            //updates the average word length when item is removed from set
            updateAvg();

            //Dismiss Keypad
            etIndex.onEditorAction(EditorInfo.IME_ACTION_DONE);

            //Resets edit text
            etIndex.getText().clear();

        } else if(etIndexString.isEmpty()){
            etIndex.setError("Please Enter A Valid Index Number");
            System.out.println("error");
        }
        else{
            etIndex.setError("Please Enter A Valid Index Number");
        }

        //Changes textView color to orange
        int color = ChangeColor.getColor();
        etIndex.setBackgroundColor(color);
    }
}
