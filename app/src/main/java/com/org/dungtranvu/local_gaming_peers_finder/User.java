package com.org.dungtranvu.local_gaming_peers_finder;

/**
 * Created by dungtranvu on 7/1/15.
 */
public class User {
    private String SummonerID;
    private String Username;
    private double Longtitude;
    private double Lattitude;
    private double winrate;
    private int region;
    User(String Username, String SummonerID, double Longtitude, double Lattitude, int wins, int losses) {
        this.Username = Username;
        this.SummonerID = SummonerID;
        this.Longtitude = Longtitude;
        this.Lattitude = Lattitude;
        if (losses == 0)
            this.winrate = 100;
        else this.winrate = (wins/(wins + losses)) * 100;
    }
    public void setSummonerID(String summonerID) {
        this.SummonerID = summonerID;
    }
    public void setUsername(String Username) {
        this.Username = Username;
    }
    public void setLongtitude(double Longtitude) {
        this.Longtitude = Longtitude;
    }
    public void setLattitude(double Lattitude) {
        this.Lattitude = Lattitude;
    }
    public void setWinrate(int wins, int losses) {
        this.winrate = (double) wins / (wins + losses);
    }

    public String getSummonerID() {
        return SummonerID;
    }

    public String getUsername() {
        return Username;
    }

    public double getLongtitude() {
        return Longtitude;
    }

    public double getLattitude() {return Lattitude;}

    public double getWinrate() {return winrate;}


}
