package com.example.aaron.talker;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by s2k on 10/12/2015.
 */
public class BTDeviceLIstFragment extends ListFragment {
    private String[] deviceNames;
    private ArrayList<BTDeviceHolder> btDeviceO;
    private final String MY_UUID_STRING = "12ce62cb-60a1-4edf-9e3a-ca889faccd6c"; //from www.uuidgenerator.net

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getActivity().getIntent();
        btDeviceO=(ArrayList<BTDeviceHolder>)i.getSerializableExtra("btlist");
        deviceNames = new String[btDeviceO.size()];
        for(int j=0; j<btDeviceO.size(); j++){
            BTDeviceHolder bth = btDeviceO.get(j);
            String s = bth.getDeviceName();
            deviceNames[j] = s;
        }

        setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, deviceNames));
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
//                    mBluetoothAdapter.cancelDiscovery();
//                    ConnectThread client = new ConnectThread(btDeviceO.get(position).getBTDevice());
//                    client.start();
        ((MainActivity)getActivity()).conWithThis(btDeviceO.get(position).getBTDevice());
    }

/*    ///////////////////////////////////// Client Thread to talk to Server here //////////////////////

    private class ConnectThread extends Thread { //from android developer
        private final BluetoothSocket mmSocket;
        private final BluetoothDevice mmDevice;

        public ConnectThread(BluetoothDevice device) {
            // Use a temporary object that is later assigned to mmSocket,
            // because mmSocket is final
            BluetoothSocket tmp = null;
            mmDevice = device;

            // Get a BluetoothSocket to connect with the given BluetoothDevice
            try {
                // MY_UUID is the app's UUID string, also used by the server code
                tmp = mmDevice.createRfcommSocketToServiceRecord(MY_UUID);
            } catch (IOException e) {
                Log.i(TCLIENT, "IOException when creating RFcommSocket\n" + e);
            }
            mmSocket = tmp;
        }

        public void run() {
            // Cancel discovery because it will slow down the connection
            mBluetoothAdapter.cancelDiscovery();

            try {
                // Connect the device through the socket. This will block
                // until it succeeds or throws an exception after 12 seconds (or so)
                mmSocket.connect();
            } catch (IOException connectException) {
                Log.i(TCLIENT,"Connect IOException when trying socket connection\n" + connectException);
                // Unable to connect; close the socket and get out
                try {
                    mmSocket.close();
                } catch (IOException closeException) {
                    Log.i(TCLIENT,"Close IOException when trying socket connection\n" + closeException);
                }
                return;
            }

            // Do work to manage the connection (in a separate thread)
            manageConnectedSocket(mmSocket);      //talk to server
        }

        //manage the connection over the passed-in socket
        private void manageConnectedSocket(BluetoothSocket socket) {
            OutputStream out;
            String theMessage = "ABC";
            byte [] msg =  theMessage.getBytes();
            try {
                out = socket.getOutputStream();
                Log.i(TCLIENT,"Sending the message: [" + theMessage + "]");
                out.write(msg);
            } catch (IOException ioe) {
                Log.e(TCLIENT, "IOException when opening outputStream\n" + ioe);
                return;
            }
            //cancel();
        }

        *//** Will cancel an in-progress connection, and close the socket *//*
        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException ioe) {
                Log.e(TCLIENT, "IOException when closing outputStream\n" + ioe);
            }
        }
    }


    /////////////////////////////////////  ServerSocket stuff here ///////////////////////////

    private class AcceptThread extends Thread {  //from android developer
        private BluetoothServerSocket mmServerSocket = null;

        public AcceptThread() {
            // Use a temporary object that is later assigned to mmServerSocket,
            // because mmServerSocket is supposed to be final
            BluetoothServerSocket tmp;
            try {
                // MY_UUID is the app's UUID string, also used by the client code
                tmp = mBluetoothAdapter.listenUsingRfcommWithServiceRecord(SERVICE_NAME, MY_UUID);
            } catch (IOException e) {return; }
            Log.i(TSERVER, "AcceptThread registered the server\n");
            mmServerSocket = tmp;
        }

        public void run() {
            BluetoothSocket socket;
            // Keep listening until exception occurs or a socket is returned
            while (true) {
                Log.i(TSERVER,"Server Looking for a Connection");
                try {
                    socket = mmServerSocket.accept();  //blocks until connection made or exception
                } catch (IOException e) {
                    Log.i(TSERVER, "socket accept through an exception\n" + e);
                    break;
                }
                // If a connection was accepted
                if (socket != null) {
                    // Do work to manage the connection (in a separate thread)
                    manageConnectedSocket(socket);
                    break;
                }
            }
        }

        //manage the Server's end of the conversation on the passed-in socket
        public void manageConnectedSocket(BluetoothSocket socket) {
            Log.i(TSERVER, "\nManaging the Socket\n");
            InputStream in;
            final int nBytes;
            byte [] msg = new byte[255]; //arbitrary size
            try {
                in = socket.getInputStream();
                nBytes = in.read(msg);
                Log.i(TSERVER, "\nServer Received " + nBytes + "\n");
            } catch (IOException ioe) {
                Log.e(TSERVER, "IOException when opening inputStream\n" + ioe);
                return;
            }
            try {
                final String msgString = new String(msg, "UTF-8"); //convert byte array to string
                Log.i(TSERVER, "\nServer Received " + nBytes + "Bytes:  [" + msgString + "]\n");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        echoMsg("\nReceived " + nBytes + ":  [" + msgString + "]\n");
                    }
                });
            } catch (UnsupportedEncodingException uee) {
                Log.e(TSERVER, "UnsupportedEncodingException when converting bytes to String\n" + uee);
            } finally {
                cancel();
            }
        }

        *//** Will cancel the listening socket, and cause the thread to finish *//*
        public void cancel() {
            try {
                mmServerSocket.close();
            } catch (IOException ioe) {
                Log.e(TSERVER, "IOException when canceling serverSocket\n" + ioe);
            }
        }
    }*/
}
