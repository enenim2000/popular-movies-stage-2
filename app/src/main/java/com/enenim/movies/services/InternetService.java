package com.enenim.movies.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.enenim.movies.config.Config;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Created by enenim on 4/17/17.
 */

public class InternetService extends IntentService {
    public InternetService() {
        super("InternetServiceName");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        boolean result = isConnetedToInternet();
        Log.d("InternetService", " internet status is " + result);

        //---send a broadcast to inform the activity that internet status is checked
        Intent broadcastIntent = new Intent();
        broadcastIntent.putExtra(Config.INTERNET_STATUS_KEY, result);
        broadcastIntent.setAction(Config.INTERNET_CONNECTIVITY_ACTION_KEY);
        getBaseContext().sendBroadcast(broadcastIntent);
    }

    public Boolean isOnline() {
        try {
            Process p1 = java.lang.Runtime.getRuntime().exec("ping -c 1 www.google.com");
            int returnVal = p1.waitFor();
            boolean reachable = (returnVal==0);
            return reachable;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isConnetedToInternet() {
        try {
            int timeoutMs = 1500;
            Socket sock = new Socket();
            SocketAddress sockaddr = new InetSocketAddress("8.8.8.8", 53);

            sock.connect(sockaddr, timeoutMs);
            sock.close();

            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
