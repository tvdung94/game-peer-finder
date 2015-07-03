package com.org.dungtranvu.local_gaming_peers_finder;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import java.util.Map;
import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.common.Region;
import com.robrua.orianna.type.core.stats.PlayerStatsSummary;
import com.robrua.orianna.type.core.stats.PlayerStatsSummaryType;
import com.robrua.orianna.type.core.summoner.Summoner;
import com.robrua.orianna.type.exception.APIException;


public class showDetails extends ActionBarActivity {
    TextView summoner_name;
    TextView summoner_lvl;
    TextView summoner_ranked_wins;
    TextView summoner_ranked_losses;
    String name;
    String lvl;
    String wins="";
    String losses="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);
         summoner_name = (TextView) findViewById(R.id.textView_showDetails_Name);
         summoner_lvl = (TextView) findViewById(R.id.textView_showDetails_Level);
         summoner_ranked_wins = (TextView) findViewById(R.id.textView_showDetails_Wins);
         summoner_ranked_losses = (TextView) findViewById(R.id.textView_showDetails_Losses);
        (new GetDataFromRiot()).execute();

    }
    private class GetDataFromRiot extends AsyncTask<Void, Void, Integer> {
        int doo;
        protected Integer doInBackground(Void... Params) {
            RiotAPI.setMirror(Region.NA);
            RiotAPI.setRegion(Region.NA);
            RiotAPI.setAPIKey("f35f8670-d654-4f66-8fbd-af28ccb5ad3c");
            Bundle extra = getIntent().getExtras();
            String SummonerName;
            SummonerName = extra.getString("SummonerName");
            try {
                Summoner summoner = RiotAPI.getSummonerByName(SummonerName);
                name = summoner.getName();
                lvl = Long.toString(summoner.getLevel());
                Map<PlayerStatsSummaryType, PlayerStatsSummary> InfoMap = summoner.getStats();
                PlayerStatsSummary RankInfo = InfoMap.get(PlayerStatsSummaryType.RankedSolo5x5);
                wins = Integer.toString(RankInfo.getWins());
                losses = Integer.toString(RankInfo.getLosses());
            }
            catch (APIException e) {

            }


            //Log.e("summonername", name);
            //Log.e("summonerlvl", lvl);

            //Update winrate and rank back to server database
            doo = 1;
            return doo;
        }

        protected void onPostExecute(Integer doo) {
            summoner_name.setText(name);
            summoner_lvl.setText(lvl);
            summoner_ranked_wins.setText(wins);
            summoner_ranked_losses.setText(losses);

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
