package com.shreya.notetaking;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.shreya.notetaking.adapter.NoteListAdapter;
import com.shreya.notetaking.database.DBContract;
import com.shreya.notetaking.database.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class ListNotes extends AppCompatActivity {

    List <NoteDetails> mnotes =new ArrayList<>();
    ListView mNoteListView;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_notes);
        mNoteListView=(ListView) findViewById(R.id.noteList);

        GetDataFromDatabase getDataFromDatabase = new GetDataFromDatabase();
        getDataFromDatabase.execute();

            //TODO: create list to show notes.
    }

    private List<NoteDetails> getDataFromDatabase()  {

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

    private class GetDataFromDatabase extends AsyncTask<Void,Void,List<NoteDetails>> implements AdapterView.OnItemClickListener {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog=new ProgressDialog(ListNotes.this);
            progressDialog.setMessage("Loading..");
            progressDialog.show();

        }

        @Override
        protected List<NoteDetails> doInBackground(Void... voids) {
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

        @Override
        protected void onPostExecute(List<NoteDetails> noteDetailses) {
            super.onPostExecute(noteDetailses);

            mnotes=noteDetailses;
            NoteListAdapter noteListAdapter= new NoteListAdapter(getApplicationContext(),R.layout.cutsom_list_item,mnotes);
            mNoteListView.setAdapter(noteListAdapter);
            mNoteListView.setOnItemClickListener(this);

            progressDialog.cancel();
        }

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            NoteDetails noteDetails = (NoteDetails) adapterView.getItemAtPosition(i);
           // Toast.makeText(getApplicationContext(),"Title : "+noteDetails.getNOTE_TITLE()+" content :"+ noteDetails.getNOTE(),Toast.LENGTH_LONG).show();

            Intent showNoteIntent = new Intent(ListNotes.this, ShowNote.class);
            Bundle bundle= new Bundle();
            bundle.putSerializable("note_details",noteDetails);
            showNoteIntent.putExtras(bundle);
            startActivity(showNoteIntent);

        }
    }
}
