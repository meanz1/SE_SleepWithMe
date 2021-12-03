package com.example.lullaby;

import android.provider.BaseColumns;

public final class Database {

    public static final class CreateAccount implements BaseColumns {
        public static final String NAME = "name";
        public static final String GENDER = "gender";
        public static final String AGE = "age";
        public static final String _TABLENAME = "account";
        public static final String _CREATE = "create table if not exists "+_TABLENAME+"("
                +_ID+" integer primary key autoincrement, "
                +NAME+" text not null, "
                +GENDER+" text not null , "
                +AGE+" integer not null ," +
                "unique(name));";
    }

    public static final class CreatePreference implements BaseColumns {
        public static final String accountName = "accountName";
        public static final String category = "category";
        public static final String _TABLENAME = "preference";
        public static final String _CREATE = "create table if not exists "+_TABLENAME+"("
                +_ID+" integer primary key autoincrement, "
                +accountName+" text not null, "
                +category+" text not null, "
                +"unique(accountName, category));";
    }

    public static final class CreateRecord implements BaseColumns {
        public static final String accountName = "accountName";
        public static final String date = "date";
        public static final String achievement = "achievement";
        public static final String _TABLENAME = "record";
        public static final String _CREATE = "create table if not exists "+_TABLENAME+"("
                +_ID+" integer primary key autoincrement, "
                +accountName+" text not null, "
                +date+" text not null , "
                +achievement+" integer not null ," +
                "unique(accountName, date));";
    }
}