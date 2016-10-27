package com.shreya.notetaking.database;

import android.provider.BaseColumns;

/**
 * Created by Aman on 10/9/2016.
 */

public class DBContract implements BaseColumns {

    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.

    private DBContract()
    {

    }
    /* Inner class that defines the table contents */
    public static class DBEntry implements BaseColumns {
        public static final String TABLE_NAME = "notes";
        public static final String COLUMN_NAME_TITLE="note_title";
        public static final String COLUMN_NAME_NOTE="note";

    }





}
