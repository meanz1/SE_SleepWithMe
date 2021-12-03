package com.example.lullaby;

public class AccountData {

    private static AccountData accountData = null;
    private String name;
    private String gender;
    private int age;

    public String getName(){
        return name;
    }

    public String getGender(){
        return gender;
    }

    public int getBirth(){
        return age;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setGender(String gender){
        this.gender = gender;
    }

    public void setBirth(int age){
        this.age = age;
    }

    public static synchronized AccountData getInstance(){
        if(null==accountData){
            accountData = new AccountData();
        }
        return accountData;
    }

}
