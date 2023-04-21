package me.cessentials.data.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerData {
    private UUID uuid;
    private int kills;
    private int deaths;
    private boolean flight;
    private List<PlayerHome> playerHomeList;

    public PlayerData(UUID uuid) {
        this.uuid = uuid;
        kills = 0;
        deaths = 0;
        flight = false;
        playerHomeList = new ArrayList<>();
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public List<PlayerHome> getPlayerHomeList() {
        return playerHomeList;
    }

    public void setPlayerHomeList(List<PlayerHome> playerHomeList) {
        this.playerHomeList = playerHomeList;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public boolean isFlight() {
        return flight;
    }

    public void setFlight(boolean flight) {
        this.flight = flight;
    }
}
