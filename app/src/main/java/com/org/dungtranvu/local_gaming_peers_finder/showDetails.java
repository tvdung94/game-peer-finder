package com.org.dungtranvu.local_gaming_peers_finder;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.common.Region;
import com.robrua.orianna.type.core.summoner.Summoner;

public class showDetails extends ActionBarActivity {
    TextView summoner_name;
    TextView summoner_lvl;
    String name;
    String lvl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);
         summoner_name = (TextView) findViewById(R.id.textView_showDetails_Name);
         summoner_lvl = (TextView) findViewById(R.id.textView_showDetails_Level);
        (new GetDataFromRiot()).execute();

    }
    private class GetDataFromRiot extends AsyncTask<Void, Void, Integer> {
        int doo;
        protected Integer doInBackground(Void... Params) {
            RiotAPI.setMirror(Region.NA);
            RiotAPI.setRegion(Region.NA);
            RiotAPI.setAPIKey("f35f8670-d654-4f66-8fbd-af28ccb5ad3c");
            Summoner summoner = RiotAPI.getSummonerByName("1mEther");


            name = summoner.getName();
            lvl = Long.toString(summoner.getLevel());
            Log.e("summonername", name);
            Log.e("summonerlvl", lvl);
            doo = 1;
            return doo;
        }

        protected void onPostExecute(Integer doo) {
            summoner_name.setText(name);
            summoner_lvl.setText(lvl);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_details, menu);
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
}
