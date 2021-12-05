package com.example.lullaby.data;

import com.example.lullaby.MyService;

public class GlobalVariable {
    private static GlobalVariable instance = null;
    private boolean checkLogin = false;
    private boolean yetSleep = false;
    public boolean getYetSleep() { return yetSleep; }
    public void setYetSleep(boolean yetSleep) { this.yetSleep = yetSleep; }

    private MyService ms;
    public MyService getMs() { return ms; }
    public void setMs(MyService ms) { this.ms = ms; }
    public boolean getCheckLogin(){
        return this.checkLogin;
    }
    public void setCheckLogin(boolean check){
        this.checkLogin = check;
    }

    public static synchronized GlobalVariable getInstance() {
        if(instance == null) {
            instance = new GlobalVariable();
        }
        //
        return instance;
    }
}
