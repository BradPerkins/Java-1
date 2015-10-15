package com.example.bradperkins.project_3;

/**
 * Created by bradperkins on 10/14/15.
 */

//Custom Objects Class
public class Teams {

    private String mName;
    private String mRecord;
    private int mLogo;


    //Parameter for constructor
    public Teams(){
        mName = mRecord = "";
    }

    //Constructor
    public Teams(String _name, String _record){
        mName = _name;
        mRecord = _record;
    }

    public Teams(String _name, String _record, int _logo){
       this(_name, _record);
        mLogo = _logo;
    }

    //Getters
    public String getName(){

        return mName;
    }

    public String getRecord(){

        return mRecord;
    }

    public int getLogo(){
        return mLogo;
    }


    public String toString(){
        return mName;
    }

}
