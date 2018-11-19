package dev2dev.devicetodevice;

import android.net.wifi.p2p.WifiP2pDevice;

public class PeerDevice {
    private WifiP2pDevice Device;

    public PeerDevice(WifiP2pDevice device) {
        this.Device = device;
    }

    public WifiP2pDevice getDevice() {
        return this.Device;
    }

}
