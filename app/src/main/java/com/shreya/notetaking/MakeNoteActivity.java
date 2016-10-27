package com.shreya.notetaking;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.shreya.notetaking.database.DBContract;
import com.shreya.notetaking.database.DBHelper;

import static com.shreya.notetaking.R.id.note;

public class MakeNoteActivity extends AppCompatActivity implements View.OnClickListener {


    Button btn_createnote;

    LinedEditText mLinedEditText;

    EditText et_noteTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_note);


        btn_createnote=(Button) findViewById(R.id.createnote);

        mLinedEditText=(LinedEditText) findViewById(note);

        et_noteTitle=(EditText)findViewById(R.id.note_title);

        btn_createnote.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int btn_id= view.getId();

        switch (btn_id)
        {
            case R.id.createnote:

                String note_title= et_noteTitle.getText().toString();
                String note= mLinedEditText.getText().toString();

                insertNoteInDB(note_title,note);
                break;
        }
    }

    private void insertNoteInDB(String note_title, String note) {

        DBHelper mDbHelper= new DBHelper(getApplicationContext());
        SQLiteDatabase mSqLiteDatabase= mDbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DBContract.DBEntry.COLUMN_NAME_TITLE,note_title);
        contentValues.put(DBContract.DBEntry.COLUMN_NAME_NOTE,note);

        mSqLiteDatabase.insert(DBContract.DBEntry.TABLE_NAME,null,contentValues);






    }
}
