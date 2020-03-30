package android.kaviles.bletutorial;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Kelvin on 5/7/16.
 */
public class ListAdapter_BTLE_Devices extends ArrayAdapter<BTLE_Device> {

    Activity activity;
    int layoutResourceID;
    ArrayList<BTLE_Device> devices;

    public ListAdapter_BTLE_Devices(Activity activity, int resource, ArrayList<BTLE_Device> objects) {
        super(activity.getApplicationContext(), resource, objects);

        this.activity = activity;
        layoutResourceID = resource;
        devices = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater =
                    (LayoutInflater) activity.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layoutResourceID, parent, false);
        }

        BTLE_Device device=devices.get(position);
        String name=device.getName();
        String address=device.getUUID();
        int rssi=device.getRSSI();

        TextView nm=(TextView) convertView.findViewById(R.id.tv_name);
        if(name!=null && name.length()>0)
        {
            nm.setText(device.getName());
        }
        else{
            nm.setText("No Name");
        }
        TextView tvrssi=(TextView) convertView.findViewById(R.id.tv_rssi);
        tvrssi.setText("RSSI: " + Integer.toString(rssi));
        TextView tvmac=(TextView) convertView.findViewById(R.id.tv_macaddr);
        if (address!=null && address.length()>0)
            tvmac.setText("");
            //tvmac.setText(device.getUUID());
       // else
            //tvmac.setText("No Address");
        return convertView;
    }
}
