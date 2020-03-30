package android.kaviles.bletutorial;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;

import android.os.Handler;

public class Scanner_BTLE {

    private MainActivity ma;
    private BluetoothAdapter bluetoothAdapter;
    private boolean mScanning;
    private Handler mHandler;
    private long scanPeriod;
    private long signalStrength;
    public Scanner_BTLE(MainActivity mainActivity,long scanPeriod , int signalStrength){
        ma=mainActivity;

        mHandler=new Handler();

        this.scanPeriod=scanPeriod;
        this.signalStrength=signalStrength;

        final BluetoothManager bluetoothManager=(BluetoothManager) ma.getSystemService(Context.BLUETOOTH_SERVICE);
        bluetoothAdapter=bluetoothManager.getAdapter();
    }

    public boolean ismScanning(){
        return mScanning;
    }

    public void start()
    {
        if(!Utils.checkBluetooth(bluetoothAdapter)){
            Utils.requestUserBluetooth(ma);
            ma.stopScan();
        }
        else
        {
            scanLeDevice(true);
        }
    }
    public void stop(){
        scanLeDevice(false);
    }
    private void scanLeDevice(final boolean enable){
        if(enable && !mScanning)
        {
            Utils.toast(ma.getApplication(), "Start BLE Scan...");
            mHandler.postDelayed(new Runnable(){
                @Override
                public void run() {
                    Utils.toast(ma.getApplicationContext(), "Stopping BLE Scan...");

                    mScanning=false;
                    bluetoothAdapter.stopLeScan(mLeScanCallback);

                    ma.stopScan();
                }
            }, scanPeriod);

            mScanning=true;
            bluetoothAdapter.startLeScan(mLeScanCallback);
        }
    }

    private BluetoothAdapter.LeScanCallback mLeScanCallback=
            new BluetoothAdapter.LeScanCallback() {
                @Override
                public void onLeScan(final BluetoothDevice device, int rssi, final byte[] scanRecord) {
                    final int new_rssi=rssi;
                    if (rssi<signalStrength){
                        mHandler.post(new Runnable(){
                            @Override
                            public void run() {
                                ma.addDevice(device, new_rssi);
                            }
                        });
                    }
                }
            };
}
