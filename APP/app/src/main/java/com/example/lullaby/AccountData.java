package com.example.lullaby;

public class AccountData {

    private static AccountData accountData = null;
    private String name;
    private String gender;
    private int birth;

    public String getName(){
        return name;
    }

    public String getGender(){
        return gender;
    }

    public int getBirth(){
        return birth;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setGender(String gender){
        this.gender = gender;
    }

    public void setBirth(int birth){
        this.birth = birth;
    }

    public static synchronized AccountData getInstance(){
        if(null==accountData){
            accountData = new AccountData();
        }
        return accountData;
    }

}
