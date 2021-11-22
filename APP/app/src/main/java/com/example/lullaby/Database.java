package com.example.lullaby;

import android.provider.BaseColumns;

public final class Database {

    public static final class CreateDB implements BaseColumns {
        public static final String NAME = "name";
        public static final String GENDER = "gender";
        public static final String AGE = "age";
        public static final String _TABLENAME = "accountdata";
        public static final String _CREATE = "create table if not exists "+_TABLENAME+"("
                +_ID+" integer primary key autoincrement, "
                +NAME+" text not null , "
                +GENDER+" text not null , "
                +AGE+" integer not null );";
    }

}