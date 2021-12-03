package com.example.lullaby;

public class GlobalVariable {
    private static GlobalVariable instance = null;

    private boolean yetSleep = false;
    public boolean getYetSleep() { return yetSleep; }
    public void setYetSleep(boolean yetSleep) { this.yetSleep = yetSleep; }

    private MyService ms;
    public MyService getMs() { return ms; }
    public void setMs(MyService ms) { this.ms = ms; }

    public static synchronized GlobalVariable getInstance() {
        if(instance == null) {
            instance = new GlobalVariable();
        }
        return instance;
    }
}
