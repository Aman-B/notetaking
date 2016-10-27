package com.shreya.notetaking;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.shreya.notetaking.database.DBContract;
import com.shreya.notetaking.database.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class ListNotes extends AppCompatActivity {

    List <NoteDetails> mnotes =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_notes);

        mnotes=getDataFromDatabase();

            //TODO: create list to show notes.
    }

    private List<NoteDetails> getDataFromDatabase() {

        List<NoteDetails> noteList=new ArrayList<NoteDetails>();

        DBHelper mDbHelper= new DBHelper(getApplicationContext());
        SQLiteDatabase mSqLiteDatabase= mDbHelper.getReadableDatabase();

        Cursor mCursor= mSqLiteDatabase.rawQuery("select * from "+ DBContract.DBEntry.TABLE_NAME,null);

        if(mCursor.moveToFirst())
        {
            do
            {
                NoteDetails mQuestion= new NoteDetails();
                mQuestion.setNOTE_TITLE(mCursor.getString(1));
                mQuestion.setNOTE(mCursor.getString(2));

                noteList.add(mQuestion);
            }while(mCursor.moveToNext());
        }

        return noteList;


    }
}
