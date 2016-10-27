package com.shreya.notetaking;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.shreya.notetaking.database.DBContract;
import com.shreya.notetaking.database.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    Button btn_makenote,btn_viewnotes,btn_testdb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_makenote =(Button) findViewById(R.id.makenote);
        btn_viewnotes=(Button) findViewById(R.id.viewnotes);
        btn_testdb=(Button) findViewById(R.id.testbutton);


        //set onclick listeners:
        btn_makenote.setOnClickListener(this);
        btn_viewnotes.setOnClickListener(this);
        btn_testdb.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        int button_id= view.getId();

        Intent intent;

        switch (button_id)
        {
            case R.id.makenote:
                intent=new Intent(MainActivity.this,MakeNoteActivity.class);
                startActivity(intent);
                break;

            case R.id.viewnotes:
                intent=new Intent(MainActivity.this,ListNotes.class);
                startActivity(intent);
                break;

            case R.id.testbutton:
                showdb();
                break;



        }
    }

    private void showdb() {

        List<NoteDetails> questionList=new ArrayList<NoteDetails>();

        DBHelper mDbHelper= new DBHelper(getApplicationContext());
        SQLiteDatabase mSqLiteDatabase= mDbHelper.getReadableDatabase();

        Cursor mCursor= mSqLiteDatabase.rawQuery("select * from "+ DBContract.DBEntry.TABLE_NAME,null);

        if(mCursor.moveToFirst())
        {
            do
            {
                NoteDetails mnotedetails= new NoteDetails();
                mnotedetails.setNOTE_TITLE(mCursor.getString(1));
                mnotedetails.setNOTE(mCursor.getString(2));

                questionList.add(mnotedetails);
            }while(mCursor.moveToNext());
        }

        Toast.makeText(getApplicationContext(),"Data : "+questionList.get(0).getNOTE_TITLE()+questionList.get(0).getNOTE(),Toast.LENGTH_LONG).show();

    }
}
