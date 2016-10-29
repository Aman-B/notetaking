package com.shreya.notetaking.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.shreya.notetaking.NoteDetails;
import com.shreya.notetaking.R;

import java.util.List;

/**
 * Created by Aman on 10/28/2016.
 */

public class NoteListAdapter extends ArrayAdapter<NoteDetails>{

    Context mContext;

    List<NoteDetails>data;

    public NoteListAdapter(Context context, int resource, List<NoteDetails>objects) {
        super(context, resource, objects);
        mContext=context;
        data=objects;
        Log.i("Data in adapter :1 ", " "+data.size());


    }



    static class ViewHolder{
        TextView note_title;
        TextView note;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Log.i("called : ", " bro");
        ViewHolder viewHolder = new ViewHolder();

        if(convertView==null)
        {
            LayoutInflater mLayoutInflater =(LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView =mLayoutInflater.inflate(R.layout.cutsom_list_item,parent,false);

            viewHolder.note_title=(TextView) convertView.findViewById(R.id.noteTitle);
            viewHolder.note=(TextView) convertView.findViewById(R.id.note);

            convertView.setTag(viewHolder);


        }
        else
        {
            viewHolder=(ViewHolder) convertView.getTag();

        }

        Log.i("Data in adapter : ", " "+data);

        if(data.size()!=0) {
            Log.i("Process is ", "in!"+data.get(position).getNOTE_TITLE()+ " note "+data.get(position).getNOTE());
            viewHolder.note_title.setText(data.get(position).getNOTE_TITLE());
            viewHolder.note.setText(data.get(position).getNOTE());
        }
        else{
            viewHolder.note_title.setText(R.string.test_title);
            viewHolder.note.setText(R.string.test_note);
        }





        return convertView;
    }
}
