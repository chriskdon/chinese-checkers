package ca.brocku.chinesecheckers.network;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import ca.brocku.chinesecheckers.R;

/**
 * @author kubasub
 * @date March 27, 2014
 */
public class NetworkActivity extends Activity {
    public NetworkStateReceiver networkStateReceiver = new NetworkStateReceiver();
    private LinearLayout networkConnectivityContainer;


    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(R.layout.activity_network);

        networkConnectivityContainer = (LinearLayout)findViewById(R.id.networkConnectivityContainer);

        FrameLayout subActivityContainer = (FrameLayout)findViewById(R.id.activityLayoutContainer);
        getLayoutInflater().inflate(layoutResID, subActivityContainer);
        //subActivityContainer.addView();
    }

    @Override
    protected void onResume() {
        super.onResume();

        registerReceiver(networkStateReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE")); //for changes in connectivity
    }

    @Override
    protected void onPause() {
        super.onPause();

        unregisterReceiver(networkStateReceiver);
    }

    protected void onNetworkConnected() {
        networkConnectivityContainer.setVisibility(View.GONE);
    }

    protected void onNetworkDisconnected() {
        networkConnectivityContainer.setVisibility(View.VISIBLE);
    }

    private class NetworkStateReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getExtras()!=null) {
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
                NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

                if((mobile!=null && mobile.isConnected()) || (wifi!=null && wifi.isConnected())) {
                    NetworkActivity.this.onNetworkConnected();
                } else {
                    NetworkActivity.this.onNetworkDisconnected();
                }
            }
        }
    }
}
