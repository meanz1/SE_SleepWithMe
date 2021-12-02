package com.example.lullaby.data;

public class Profile {
    public int idx;
    public String userId;
    public String name;
    public String gender;
    public int age;
    public String category1;
    public String category2;

    public Profile(){}
    public Profile(int idx, String userId, String name, String gender, int age, String category1, String category2){
        this.idx = idx;
        this.userId = userId;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.category1 = category1;
        this.category2 = category2;
    }

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

    public void setUserId(String userId) { this.userId = userId; }

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
}
