package com.org.dungtranvu.local_gaming_peers_finder;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class Menu_activity extends ActionBarActivity {
    Socket socket;

    //SEVER IP AND PORT
    private static final int SERVERPORT = 8000;
    private static final String SERVER_IP = "192.168.100.4";
    TextView tv;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_activity);

        //KAKAO LOGIN BUTTON CODE GOES HERE

    }
    public void sign_in(View v) {
        // YOUR AUTH CODE GOES HERE

        startActivity(new Intent(Menu_activity.this, MainActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private class LoginOperantion extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            /*TelephonyManager telemamanger = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
            String getPhoneNumber = telemamanger.getLine1Number();
            Log.d("Here", getPhoneNumber);

            //Open client and connect to the server;
            try {
                socket  = new Socket(SERVER_IP, SERVERPORT);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                out.write("login " + getPhoneNumber + "\r\n" );
                out.flush();
                String msg = in.readLine();
                Log.d("The message is", msg);
                if (msg == "OK\r\n") {
                    Intent i= new Intent(Menu_activity.this, MainActivity.class);
                    //i1.p
                    socket.close();
                    startActivity(i);
                }
                else if (msg.equals( "Connected")) {
                    Intent i = new Intent(Menu_activity.this, Register_Activity.class);
                    socket.close();
                    startActivity(i);
                }
                finish();

            }
            catch (UnknownHostException e) {

            }
            catch (IOException e1) {
                e1.printStackTrace();
            }*/

            //CLIENT CODE GOES HERE




            finish();

            return null;
        }

        protected void onPreExecute()
        {
           // Do UI stuff before the thread runs
        }

        protected void onPostExecute() {
            // Do UI stuff after the thread is done executing
        }
    }
}
