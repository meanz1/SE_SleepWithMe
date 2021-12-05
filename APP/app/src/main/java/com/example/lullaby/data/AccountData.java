package com.example.lullaby.data;

import java.util.ArrayList;

public class AccountData {

    private static AccountData accountData = null;

    private int userSelected = -1;
    public ArrayList<Profile> profiles;

    AccountData() {
        profiles = new ArrayList<Profile>();
    }

    public void setUserSelected(int sel){this.userSelected = sel;}
    public int getUserSelected(){return this.userSelected;}

    public static synchronized AccountData getInstance(){
        if(null==accountData){
            accountData = new AccountData();
        }
        return accountData;
    }

}
