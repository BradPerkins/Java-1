package com.example.bradperkins.project_4;


/**
 * Created by bradperkins on 10/20/15.
 */
public class Movie {

    private static String mName;
    private static String mYear;
    private static String mSyn;


    public Movie(){
        mName = mYear = mSyn =  "";
    }

    public Movie(String _name, String _year, String _syn){
        mName = _name;
        mYear = _year;
        mSyn = _syn;
    }

    public static String getName(){
        return mName;
    }

    public static String getYear(){
        return mYear;
    }

    public static String getSyn(){
        return mSyn;
    }

}


