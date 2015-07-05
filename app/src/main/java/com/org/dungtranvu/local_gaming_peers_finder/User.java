package com.org.dungtranvu.local_gaming_peers_finder;

/**
 * Created by dungtranvu on 7/1/15.
 */
public class User {
    private String SummonerID;
    private String Username;
    private double Longtitude;
    private double Lattitude;
    private String winrate;
    private String region;
    User(String Username, String SummonerID, double Longtitude, double Lattitude, String winrate, String region) {
        this.Username = Username;
        this.SummonerID = SummonerID;
        this.Longtitude = Longtitude;
        this.Lattitude = Lattitude;
        this.winrate = winrate;
        this.region = region;
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
    public void setWinrate(String winrate) {
        this.winrate = winrate;
    }
    public void setRegion(String region) { this.region = region; }

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

    public String getWinrate() {return winrate;}

    public String getRegion() {return region; }


}
