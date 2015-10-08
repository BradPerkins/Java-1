
//Bradley Perkins
// Project 1
// Java 1

package com.example.bradperkins.project1;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText etComic;
    TextView numOfItems;
    TextView aveLength;
    EditText etIndex;

    //Arrays   HashSet holds only unique strings
    HashSet<String> dataSet = new HashSet<>();
    List<String> list = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
            System.out.println(dataSetTotal);

            Toast.makeText(MainActivity.this, "Comic titled '" + etComic.getText().toString() + "' added!!!!!", Toast.LENGTH_SHORT).show();

            numOfItems.setText(String.valueOf(dataSet.size()));
            dataSet.add(etComic.getText().toString());
            //etComic.setText("");
            etComic.getText().clear();
            //Dismiss Keypad
            etComic.onEditorAction(EditorInfo.IME_ACTION_DONE);
            //numOfItems.setText(dataSetTotal);

            //Finds the Average Length of words in array
            int lengthAvg = 0;
            for(String lengthNum : dataSet){
                lengthAvg = lengthAvg + lengthNum.length();

                //Sets the Number of items in the array in "Number of Items" label
                numOfItems.setText(String.valueOf(dataSet.size()));
                String stringLength = String.valueOf(lengthAvg / dataSet.size());

                //Sets the Number for the "Average Length" Label
                aveLength.setText(stringLength);

            }
        }
        //System.out.println(dataSet);
    }


    public void indexButton(View view) {

        etIndex = (EditText) findViewById(R.id.etIndex);
        String etIndexString = etIndex.getText().toString();
        String[] newArray = dataSet.toArray(new String[dataSet.size()]);
        int numEntered = Integer.valueOf(String.valueOf(etIndex.getText()));

        String text = etIndex.getText().toString().trim();


        if (numEntered < newArray.length ) {
            //Toast alert
            AlertDialog toastAlert = new Builder(this).create();
            toastAlert.setTitle("You Indexed: ");
            //Fills in the toast message with string name thats called in the index
            toastAlert.setMessage(" " + newArray[numEntered].toString() + " ");
            toastAlert.setButton(DialogInterface.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(), "You Clicked OK", Toast.LENGTH_SHORT).show();
                }
            });
            //Dismiss Keypad
            etIndex.onEditorAction(EditorInfo.IME_ACTION_DONE);
            toastAlert.show();
            //etIndex.setText("");

            //Resets editText
            etIndex.getText().clear();

        } else if(etIndexString.isEmpty()){
            etIndex.setError("Please Enter A Valid Index Number");
        }
        else{
            etIndex.setError("Please Enter A Valid Index Number");
        }
    }
}
