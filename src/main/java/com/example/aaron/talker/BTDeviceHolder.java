package com.example.aaron.talker;

import android.bluetooth.BluetoothDevice;

/**
 * Created by s2k on 10/12/2015.
 */
public class BTDeviceHolder {
    private BluetoothDevice btDevice;
    private String deviceName;

    public BTDeviceHolder(BluetoothDevice btd, String dName){
        btDevice = btd;
        deviceName = dName;
    }

    public String getDeviceName(){
        return deviceName;
    }

    public BluetoothDevice getBTDevice(){
        return btDevice;
    }
}
