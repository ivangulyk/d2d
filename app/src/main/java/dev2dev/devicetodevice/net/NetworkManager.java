package com.noelalgora.d2dnetwork.net;

public class NetworkManager {

    static NetworkManager instance;

    public static NetworkManager getInstance()
    {
        if(instance == null){
            instance = new NetworkManager();
        }
        return instance;
    }

    private NetworkManager()
    {
    }

    public void useAppContext() {
        //Context appContext = InstrumentationRegistry.getTargetContext();
        //assertEquals("com.noelalgora.d2dnetwork", appContext.getPackageName());
    }
}
