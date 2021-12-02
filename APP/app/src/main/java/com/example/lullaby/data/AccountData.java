package com.example.lullaby.data;

import java.util.ArrayList;

public class AccountData {

    private static AccountData accountData = null;

    public ArrayList<Profile> profiles;

    AccountData() {
        profiles = new ArrayList<Profile>();
    }

    public static synchronized AccountData getInstance(){
        if(null==accountData){
            accountData = new AccountData();
        }
        return accountData;
    }

}
