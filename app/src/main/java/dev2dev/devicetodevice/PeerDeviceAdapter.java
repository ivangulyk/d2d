package dev2dev.devicetodevice;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class PeerDeviceAdapter extends BaseAdapter {
    private android.content.Context Context;
    private List<PeerDevice> PeerDevices;

    public PeerDeviceAdapter(Context context, List<PeerDevice> devices) {
        this.Context = context;
        this.PeerDevices = devices;
    }

    public void updatePeerDevices(List<PeerDevice> devices) {
        this.PeerDevices = devices;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return this.PeerDevices.size();
    }

    @Override
    public Object getItem(int position) {
        return this.PeerDevices.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) this.Context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // inflate view
        View itemView = inflater.inflate(R.layout.list_content, parent, false);
        TextView txtDeviceAddress = (TextView) itemView.findViewById(R.id.device_name);
        // get item
        PeerDevice device = this.PeerDevices.get(position);
        // set properties
        txtDeviceAddress.setText(device.getDevice().deviceName);
        return itemView;
    }
}
