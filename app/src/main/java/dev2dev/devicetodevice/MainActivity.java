package dev2dev.devicetodevice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private d2dAplication Application;
    private Button btnSearch;
    private ListView listDevices;
    private PeerDeviceAdapter peerDeviceAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.Application = (d2dAplication) getApplication();
        this.btnSearch = (Button)(findViewById(R.id.btn));
        this.listDevices = (ListView)(findViewById(R.id.list_device));

        this.peerDeviceAdapter = new PeerDeviceAdapter(this, new ArrayList<PeerDevice>());
        this.listDevices.setAdapter(this.peerDeviceAdapter);

        this.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.startPeerDiscovery();
            }
        });
    }

    private void startPeerDiscovery() {
        this.Application.P2pHandler.Manager.discoverPeers(this.Application.P2pHandler.Channel, new WifiP2pManager.ActionListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(MainActivity.this, "Started P2P search ...", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int reason) {
                Toast.makeText(MainActivity.this, "P2P search failed with code " + String.valueOf(reason), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Intent generateServiceIntent() {
        Intent serviceIntent = new Intent(this, d2dService.class);
        serviceIntent.setAction("PEER_ACTIVITY");
        return serviceIntent;
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.bindService(this.generateServiceIntent(), this.ServiceConn, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.Binder.unregisterActivity();
        this.unbindService(this.ServiceConn);
    }

    private d2dService.PeerBinder Binder;

    private ServiceConnection ServiceConn = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            MainActivity.this.Binder = (d2dService.PeerBinder) binder;
            MainActivity.this.Binder.registerActivity(MainActivity.this);
            MainActivity.this.updateDevices();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            MainActivity.this.Binder = null;
        }
    };

    public void updateDevices() {
        if (this.Binder != null) {
            this.peerDeviceAdapter.updatePeerDevices(this.Binder.getDevices());
        }
    }
}
