package com.example.lullaby;

public class AccountData {

    private static AccountData accountData = null;
    private int idx;
    private String userId;
    private String name;
    private String gender;
    private int age;
    private String category1;
    private String category2;

    public int getIdx() { return idx;}

    public String getUserId() { return userId; }

    public String getName(){
        return name;
    }

    public String getGender(){
        return gender;
    }

    public int getAge(){
        return age;
    }

    public String getCategory1() { return category1; }

    public String getCategory2() { return category2; }

    public void setName(String name){
        this.name = name;
    }

    public void setIdx(int idx) { this.idx = idx; }

    public void setGender(String gender){
        this.gender = gender;
    }

    public void setAge(int age){
        this.age = age;
    }

    public void setCategory1(String category1) {
        this.category1 = category1;
    }

    public void setCategory2(String category2) {
        this.category2 = category2;
    }

    public static synchronized AccountData getInstance(){
        if(null==accountData){
            accountData = new AccountData();
        }
        return accountData;
    }

}
