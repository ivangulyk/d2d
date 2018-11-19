package dev2dev.devicetodevice;

import android.app.Application;

import dev2dev.devicetodevice.net.wifip2p.WifiP2pHandler;

public class d2dAplication extends Application {

    public WifiP2pHandler P2pHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        this.P2pHandler = new WifiP2pHandler(this);
    }


}
