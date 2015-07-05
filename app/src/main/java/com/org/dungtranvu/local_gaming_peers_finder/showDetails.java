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
    TextView summoner_solo_ranked_winrate;
    TextView summoner_team5x5_ranked_winrate;
    TextView summoner_unranked_winrate;
    String name;
    String lvl;
    String ranked_solo_winrate;
    String total_ranked_solo_matches;
    String ranked_team_winrate;
    String total_ranked_team_matches;
    String unranked_winrate;
    String total_unranked_matches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);
         summoner_name = (TextView) findViewById(R.id.textView_showDetails_Name);
         summoner_lvl = (TextView) findViewById(R.id.textView_showDetails_Level);
         summoner_solo_ranked_winrate = (TextView) findViewById(R.id.textView_showDetails_RankedSolo_Winrate);
         summoner_team5x5_ranked_winrate = (TextView) findViewById(R.id.textView_showDetails_Ranked5x5_Winrate);
         summoner_unranked_winrate = (TextView) findViewById(R.id.textView_showDetails_Unranked_Winrate);
        (new GetDataFromRiot()).execute();

    }
    private class GetDataFromRiot extends AsyncTask<Void, Void, Integer> {
        int doo;
        protected Integer doInBackground(Void... Params) {

            Bundle extra = getIntent().getExtras();
            String SummonerName = extra.getString("SummonerName");
            String region = extra.getString("Region");
            switch (region) {
                case "NA" :
                    RiotAPI.setMirror(Region.NA);
                    RiotAPI.setRegion(Region.NA);
                    break;
                case "KR" :
                    RiotAPI.setMirror(Region.KR);
                    RiotAPI.setRegion(Region.KR);
                    break;
                case "EUW":
                    RiotAPI.setMirror(Region.EUW);
                    RiotAPI.setRegion(Region.EUW);
                    break;
                case "LAN":
                    RiotAPI.setMirror(Region.LAN);
                    RiotAPI.setRegion(Region.LAN);
                    break;
                case "LAS":
                    RiotAPI.setMirror(Region.LAS);
                    RiotAPI.setRegion(Region.LAS);
                    break;
                case "OCE":
                    RiotAPI.setMirror(Region.OCE);
                    RiotAPI.setRegion(Region.OCE);
                    break;
                case "PBE":
                    RiotAPI.setMirror(Region.PBE);
                    RiotAPI.setRegion(Region.PBE);
                    break;
                case "RU":
                    RiotAPI.setMirror(Region.RU);
                    RiotAPI.setRegion(Region.RU);
                    break;
                case "TR":
                    RiotAPI.setMirror(Region.TR);
                    RiotAPI.setRegion(Region.TR);
                    break;
                case "BR":
                    RiotAPI.setMirror(Region.BR);
                    RiotAPI.setRegion(Region.BR);
                    break;
                case "EUNE":
                    RiotAPI.setMirror(Region.EUNE);
                    RiotAPI.setRegion(Region.EUNE);
                    break;
            }
            RiotAPI.setAPIKey("f35f8670-d654-4f66-8fbd-af28ccb5ad3c");
            try {
                Summoner summoner = RiotAPI.getSummonerByName(SummonerName);
                name = summoner.getName();
                lvl = Long.toString(summoner.getLevel());
                Map<PlayerStatsSummaryType, PlayerStatsSummary> InfoMap = summoner.getStats();

                PlayerStatsSummary RankSoloInfo = InfoMap.get(PlayerStatsSummaryType.RankedSolo5x5);
                int RankedSolo_wins = 0, RankedSolo_losses = 0;
                if (RankSoloInfo != null) {
                    RankedSolo_wins = RankSoloInfo.getWins();
                    RankedSolo_losses = RankSoloInfo.getLosses();
                }
                total_ranked_solo_matches = Integer.toString(RankedSolo_wins + RankedSolo_losses);
                ranked_solo_winrate = String.format("%.2f",(double) RankedSolo_wins/(RankedSolo_losses + RankedSolo_wins)*100);

                PlayerStatsSummary Ranked5x5Info = InfoMap.get(PlayerStatsSummaryType.RankedTeam5x5);
                int Ranked5x5_wins = 0, Ranked5x5_losses = 0;
                if (Ranked5x5Info != null) {
                    Ranked5x5_wins = Ranked5x5Info.getWins();
                    Ranked5x5_losses = Ranked5x5Info.getLosses();
                }
                total_ranked_team_matches = Integer.toString(Ranked5x5_wins + Ranked5x5_losses);
                ranked_team_winrate = String.format("%.2f", (double) Ranked5x5_wins/(Ranked5x5_wins + Ranked5x5_losses) *100);

                PlayerStatsSummary UnrankedInfo = InfoMap.get(PlayerStatsSummaryType.Unranked);
                int Unranked_wins = 0, Unranked_losses;
                if (UnrankedInfo != null) {
                    Unranked_wins = UnrankedInfo.getWins();
                    Log.d("Unraked wins", Integer.toString(Unranked_wins));
                }
                //Unranked_losses = UnrankedInfo.getLosses();
                //Log.d("Unranked losses", Integer.toString(Unranked_losses));
                total_unranked_matches = Integer.toString(Unranked_wins );
                //unranked_winrate = Double.toString((double) Unranked_wins/(Unranked_wins + Unranked_losses) *100);
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
            summoner_name.setText("Summoner: " + name);
            summoner_lvl.setText("Summoner lvl: " + lvl);
            summoner_solo_ranked_winrate.setText("Ranked solo matches played: " + total_ranked_solo_matches
            + ". Winrate: " + ranked_solo_winrate + "%.");
            summoner_team5x5_ranked_winrate.setText("Ranked 5x5 matches played: " + total_ranked_team_matches
            + ". Winrate: " + ranked_team_winrate + "%.");
            summoner_unranked_winrate.setText("Unranked wins: " + total_unranked_matches
            + ".");

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
