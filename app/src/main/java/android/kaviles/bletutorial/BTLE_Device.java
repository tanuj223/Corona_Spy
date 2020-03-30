package android.kaviles.bletutorial;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.ScanFilter;
import android.content.Intent;
import android.os.ParcelUuid;
import android.os.Parcelable;
import android.util.Log;

import java.util.Arrays;
import java.util.UUID;


public class BTLE_Device {

    private BluetoothDevice bluetoothDevice;
    private int rssi;

    public BTLE_Device(BluetoothDevice bluetoothDevice) {
        this.bluetoothDevice = bluetoothDevice;
    }

    public String getUUID() {
       ParcelUuid[] pUuid = bluetoothDevice.getUuids();
        if (pUuid==null)
            return "no";
        else
            return pUuid[3].getUuid().toString();
    }

    public String getName() {
        return bluetoothDevice.getName();
    }

    public void setRSSI(int rssi) {
        this.rssi = rssi;
    }

    public int getRSSI() {
        return rssi;
    }
}
