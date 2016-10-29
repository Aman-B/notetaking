package com.shreya.notetaking;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.shreya.notetaking.database.DBContract;
import com.shreya.notetaking.database.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class ShowNote extends AppCompatActivity implements View.OnClickListener {

    NoteDetails noteDetails;

    EditText et_note_title, et_note;
    ProgressDialog progressDialog;

    String title_toput,note_toput;

    Button btn_editnote,btn_delnote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_note);

        Intent intent = this.getIntent();

        Bundle bundle = intent.getExtras();
        noteDetails = (NoteDetails) bundle.getSerializable("note_details");

        et_note_title = (EditText) findViewById(R.id.edit_note_title);
        et_note = (EditText) findViewById(R.id.edit_note);
        btn_editnote = (Button) findViewById(R.id.edit_savenote);
        btn_delnote=(Button)findViewById(R.id.edit_deletenote);

        //set note details:

        et_note_title.setText(noteDetails.getNOTE_TITLE());
        et_note.setText(noteDetails.getNOTE());

        btn_editnote.setOnClickListener(this);
        btn_delnote.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        title_toput=et_note_title.getText().toString();
        note_toput=et_note.getText().toString();

        int id = view.getId();

        switch (id)
        {
            case R.id.edit_savenote:
                PutDataInDatabase putDataInDatabase = new PutDataInDatabase();
                putDataInDatabase.execute();
                break;


            case R.id.edit_deletenote:
                DeleteDataFromDatabase deleteDataFromDatabase = new DeleteDataFromDatabase();
                deleteDataFromDatabase.execute();
                break;



        }



    }


    private class DeleteDataFromDatabase extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(ShowNote.this);
            progressDialog.setMessage("Saving..");
            progressDialog.show();

        }

        @Override
        protected Void doInBackground(Void... voids) {
            List<NoteDetails> noteList = new ArrayList<NoteDetails>();

            DBHelper mDbHelper = new DBHelper(getApplicationContext());

            SQLiteDatabase delSqLiteDatabase = mDbHelper.getWritableDatabase();
            delSqLiteDatabase.delete(DBContract.DBEntry.TABLE_NAME,
                    ""+DBContract.DBEntry.COLUMN_NAME_TITLE+"=? and "+ DBContract.DBEntry.COLUMN_NAME_NOTE+"=?",
                    new String[]{noteDetails.getNOTE_TITLE(),noteDetails.getNOTE()});






            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.cancel();

            Intent intent = new Intent(ShowNote.this,MainActivity.class);
            startActivity(intent);


        }


    }

    private class PutDataInDatabase extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(ShowNote.this);
            progressDialog.setMessage("Saving..");
            progressDialog.show();

        }

        @Override
        protected Void doInBackground(Void... voids) {
            List<NoteDetails> noteList = new ArrayList<NoteDetails>();

            DBHelper mDbHelper = new DBHelper(getApplicationContext());

            SQLiteDatabase delSqLiteDatabase = mDbHelper.getWritableDatabase();
            delSqLiteDatabase.delete(DBContract.DBEntry.TABLE_NAME,
                    ""+DBContract.DBEntry.COLUMN_NAME_TITLE+"=? and "+ DBContract.DBEntry.COLUMN_NAME_NOTE+"=?",
                    new String[]{noteDetails.getNOTE_TITLE(),noteDetails.getNOTE()});


            ContentValues contentValues = new ContentValues();
            contentValues.put(DBContract.DBEntry.COLUMN_NAME_TITLE,title_toput);
            contentValues.put(DBContract.DBEntry.COLUMN_NAME_NOTE,note_toput);

            delSqLiteDatabase.insert(DBContract.DBEntry.TABLE_NAME,null,contentValues);

           /* SQLiteDatabase mSqLiteDatabase = mDbHelper.getReadableDatabase();


            Cursor mCursor = mSqLiteDatabase.rawQuery("select * from " + DBContract.DBEntry.TABLE_NAME, null);

            if (mCursor.moveToFirst()) {
                do {
                    NoteDetails mQuestion = new NoteDetails();
                    mQuestion.setNOTE_TITLE(mCursor.getString(1));
                    mQuestion.setNOTE(mCursor.getString(2));

                    noteList.add(mQuestion);
                } while (mCursor.moveToNext());
            }*/

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.cancel();

            Intent intent = new Intent(ShowNote.this,MainActivity.class);
            startActivity(intent);


        }


    }
}

