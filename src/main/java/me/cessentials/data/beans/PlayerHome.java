package me.cessentials.data.beans;


import org.bukkit.Location;

public class PlayerHome {

    private String homeName;
    private Location homeLocation;

    public PlayerHome(String homeName, Location homeLocation) {
        this.homeName = homeName;
        this.homeLocation = homeLocation;
    }

    public String getHomeName() {
        return homeName;
    }

    public void setHomeName(String homeName) {
        this.homeName = homeName;
    }

    public Location getHomeLocation() {
        return homeLocation;
    }

    public void setHomeLocation(Location homeLocation) {
        this.homeLocation = homeLocation;
    }
}
