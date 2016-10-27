package com.shreya.notetaking;

import java.io.Serializable;

/**
 * Created by Aman on 10/9/2016.
 */

public class NoteDetails implements Serializable{

    private int ID;



    private String NOTE_TITLE;
    private String NOTE;


    public NoteDetails()

    {

        NOTE_TITLE="";
        NOTE=" ";

    }
    //constructer for setting values
    public NoteDetails(String NOTE_TITLE,String NOTE)
    {

        this.NOTE_TITLE=NOTE_TITLE;
        this.NOTE=NOTE;
    }

    //getter and setters

    public int getID()
    {
        return ID;
    }
    public String getNOTE_TITLE() {
        return NOTE_TITLE;
    }

    public void setNOTE_TITLE(String NOTE_TITLE) {
        this.NOTE_TITLE = NOTE_TITLE;
    }
    public String getNOTE() {
        return NOTE;
    }

    public void setNOTE(String NOTE) {
        this.NOTE = NOTE;
    }




}
